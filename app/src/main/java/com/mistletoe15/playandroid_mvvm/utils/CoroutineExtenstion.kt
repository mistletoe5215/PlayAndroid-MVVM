package com.bilibili.suiseiseki.util
/**
 * Created by mistletoe
 * on 2020/8/6
 **/
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
// Quick & dirty logcat extensions
inline fun <reified T> T.logd(message: () -> String) = Log.d(T::class.simpleName, message())

inline fun <reified T> T.loge(error: Throwable, message: () -> String) = Log.d(T::class.simpleName, message(), error)

internal class CoroutineLifecycleListener(private val deferred: Deferred<*>) : LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun cancelCoroutine() {
        if (!deferred.isCancelled) {
            deferred.cancel()
        }
    }
}

// CoroutineContext running on background threads.
internal val Background = newFixedThreadPoolContext(Runtime.getRuntime().availableProcessors() * 2, "Loader")

/**
 * Creates a lazily started coroutine that runs <code>loader()</code>.
 * The coroutine is automatically cancelled using the CoroutineLifecycleListener.
 */
fun <T> LifecycleOwner.load(loader: suspend () -> T): Deferred<T> {
    val deferred = GlobalScope.async(context = Background, start = CoroutineStart.LAZY) {
        loader()
    }
    lifecycle.addObserver(CoroutineLifecycleListener(deferred))
    return deferred
}

/**
 * Extension function on <code>Deferred<T><code> that creates a launches a coroutine which
 * will call <code>await()</code> and pass the returned value to <code>block()</code>.
 */
infix fun <T> Deferred<T>.then(block: suspend (T) -> Unit): Job {
    return GlobalScope.launch() {
        try {
            block(this@then.await())
        } catch (e: Exception) {
            // Just log the exception to confirm when we get cancelled (Expect JobCancellationException)
            loge(e) { "Exception in then()!" }
            throw e
        }
    }
}
typealias IOBlockLike = ()->Boolean
typealias MainBlockLike = ()->Unit
@ExperimentalCoroutinesApi
fun ArrayList<IOBlockLike>.doIOFlowTask(certainDispatcher:CoroutineDispatcher,mainBlock: MainBlockLike){
    var taskIndex =  0
    val taskResultList:ArrayList<Boolean> = ArrayList()
    fun convert2FlowTask() = flow {
        forEach{
            emit(it())
            taskIndex++
        }
    }.flowOn(Dispatchers.IO)
    CoroutineScope(certainDispatcher+ Job()).launch {
        convert2FlowTask().buffer().onCompletion {  if (it != null) logd{"===Flow Task completed==="} }
            .catch { loge(it){"===Catch Exception==="} }
            .collect{
                taskResultList.add(it)
                logd { "Receive IOFlowTask$taskIndex result:$it" }
            }
        withContext(Dispatchers.Main){
            if(taskResultList.all { true })  mainBlock.invoke()
        }
    }
}
private lateinit var mExceptionHandler : CoroutineExceptionHandler
fun doAsyncTasksThen2Main(mScope:CoroutineScope,vararg ioBlocks:() -> Boolean,
                          mainBlock:() -> Unit,
                          exceptionBlock:((curCoroutineCtx:CoroutineContext,throwable:Throwable)->Unit)?=null,
                          ignoreBgResult:Boolean = false){
    mExceptionHandler = CoroutineExceptionHandler{ ctx,throwable->
        exceptionBlock?.invoke(ctx,throwable)
        if(mScope.coroutineContext.isActive){
            mScope.cancel()
        }
    }
    mScope.launch(mExceptionHandler) {
        val deferredList = ioBlocks.map {
            //wont interrupt each other
            async(SupervisorJob()){
                it()
            }
        }
        val result = deferredList.map { it.await() }.all { true }
        if((!ignoreBgResult && result)  || ignoreBgResult){
            withContext(Dispatchers.Main){
                mainBlock()
            }
        }
        mScope.cancel()
    }
}