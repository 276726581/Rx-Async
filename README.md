# Rx-Async

## 基于RxJava的异步任务框架

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
        }).subscribe(new NewThreadAsyncSubscribe<String>() {
            @Override
            public void call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("5: " + name);
            }
        });
```
