# Rx-Async

## 基于RxJava的异步任务框架，可以进行任务的链式调用

支持操作符: call、next、map、filter、subscribe.

支持调度器: Computation、NewThread、Computation、IO、Trampoline.

可以通过asObservable转化成Observable.

```
RxAsync.call(new ComputationAsyncCall<String>() {
            @Override
            public String call() throws Exception {
                String name = Thread.currentThread().getName();
                System.out.println("1: " + name);
                return null;
            }
        }).next(new NewThreadAsyncNextCall<String, String>() {
            @Override
            public String call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("2: " + name);
                return null;
            }
        }).map(new ComputationAsyncMap<String, String>() {
            @Override
            public String[] call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("3: " + name);
                return null;
            }
        }).filter(new TrampolineAsyncNextCall<String, Boolean>() {
            @Override
            public Boolean call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("4: " + name);
                return true;
            }
        }).next(new IOThreadAsyncNextCall<String, String>() {
            @Override
            public String call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("5: " + name);
                return null;
            }
        }).next(new IOAsyncNextCall<String, String>() {
            @Override
            public String call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("6: " + name);
                return null;
            }
        }).next(new IOAsyncNextCall<String, String>() {
            @Override
            public String call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("7: " + name);
                return null;
            }
        }).subscribe(new ImmediateAsyncSubscribe<String>() {
            @Override
            public void call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("8: " + name);
            }
        });
```
