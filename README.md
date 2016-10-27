# Rx-Async

## 基于RxJava的异步任务框架，可以进行任务的链式调用

支持操作符: call、next、map、filter、subscribe.

可以通过getObservable转化成Observable.

``` java
RxAsync.call(new RxCall<String>() {
            @Override
            public String call() throws Exception {
                String name = Thread.currentThread().getName();
                System.out.println("1: " + name);
                return "1,2,3,4,5";
            }
        }, Schedulers.computation()).flatMap(new RxFlatMap<String, String>() {
            @Override
            public String[] call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("2: " + name);
                
                String[] split = s.split(",");
                return split;
            }
        }, Schedulers.newThread()).filter(new filter<String, Boolean>() {
            @Override
            public boolean accept(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("3: " + name);
                
                if(!"3".equals(s)) {
                    return true;
                }
                return false;
            }
        }, Schedulers.io()).next(new RxNextCall<String, String>() {
            @Override
            public String call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("4: " + name);

                return "-" + s;
            }
        }, Schedulers.computation()).next(new RxNextCall<String, String>() {
            @Override
            public String call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("5: " + name);

                return s + "-";
            }
        }, Schedulers.newThread()).subscribe(new RxSubscribe<String>() {
            @Override
            public void call(String s) {
                String name = Thread.currentThread().getName();
                System.out.println("6: " + name);

                System.out.println(s);
            }
        }, Schedulers.newThread());
```
