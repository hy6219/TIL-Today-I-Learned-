# 디자인패턴1-싱글톤 패턴

**어떤 클래스(객체)가 유일하게 1개만 존재**할 때 사용

**주로 서로 자원을 공유**할 때 사용

- 예)  프린터[ 프린터 입장에서는 작업은 여러가지지만 프린터 1대가 그 업무를 다 맡음 ]
- 실제 프로그래밍에서는 TCP Socket 통신에서 서버와 연결된 connect 객체에 주로 사용됨

사용되는 형태는 아래와 같다

```java
public class A{
	//1. private한 생성자
	private A(){
	
  }
 //2. private static 접근제한을 갖는 객체 (instance)
 private static A a = new A();
//3. 외부에서 이를 접근할 수 있는 getInstance()메서드
public static A getInstance(){
   return a;
}
```

1. 우선 private 접근제한을 지닌 생성자가 존재해야 하고
2. private static 접근제한을 가진 객체 인스턴스가 존재해야 한다
3. 그리고 2의 객체를 외부에서 접근할 수 있는 public 한 메서드로, 객체를 반환해주는 메서드를 준비해야 한다

위와 같은 형태가 바로 싱글톤 패턴이라고 할 수 있다!

하지만 자원 1개를 공유하는 것을 보다 엄격히 하기 위해서는 아래와 같이 수행함이 보다 올바른 방법임을 알게 되어, 정리해보게 되었다! 앞으로는 이 방식대로 추진함이 보다 명확하고 정확할 것 같다!

```java
package com.designPattern.generator.singleton;

public class Singleton {
//1. private 한 생성자
    private Singleton(){

    }
//2.private static한 singleton 객체
    private static Singleton s;
//3.2가 null인지 확인하여 객체를 생성하고 반환
    public static Singleton getInstance(){
       if(s==null){
           //생성자로 정의되어 있지 않은 경우[static은 this를
           // 사용할 수 없기 때문에 this.s는 안됨!!]
           s = new Singleton();
       }
       return s;
    }

}
```

- 싱글톤을 사용해보고 느낀점

-싱글톤으로 사용하게 되면 자원공유가 된다는 점은 좋은데, private으로 제한을 두어서, equals를 오버라이딩할 경우에 많이 복잡해지는 것 같다

-물론 아예 방법이 없는 것이 아니라 equals로 동등성비교(값이 같은지)를 먼저 확인하고

-hashcode와 ==, System.identityHashCode(), hashCode()로 실제 주소[hashCode는 실제주소를 가리키는 지시자같은 느낌이지만 포괄적으로 사용하겠다]가 같은지 확인한다면 동일성도 확인가능하다

그렇게 된다면, 아래와 같이

위의 Singleton 클래스를 이용한 A와 B클래스를 만들고 접근해본다면

1. A클래스

```java
package com.designPattern.generator.singleton;

public class A {
    private Singleton socketClient;

    public A(){
        this.socketClient = Singleton.getInstance();
    }

    public Singleton getSingleton() {
        return this.socketClient;
    }

}
```

2. B클래스

```java
package com.designPattern.generator.singleton;

public class B {
    private Singleton socketClient;

    public B(){
        this.socketClient = Singleton.getInstance();
    }

    public Singleton getSingleton(){
        return this.socketClient;
    }
}
```

3. 위의 모든 클래스를 테스트해볼 Main 클래스

```java
package com.designPattern.generator.singleton;

public class Main {
    public static void main(String[] args){
        A a = new A();
        B b = new B();

        Singleton aClient = a.getSingleton();
        Singleton bClient = b.getSingleton();

        //equals-동등성 확인
        System.out.println(aClient.equals(bClient));
        //메모리 주소비교
        System.out.println(aClient==bClient);//true->동일객체!
        System.out.println(aClient.hashCode());//460141958
        System.out.println(bClient.hashCode());//460141958
        System.out.println(System.identityHashCode(aClient));//460141958
        System.out.println(System.identityHashCode(bClient));//460141958
    }
}
```

위와 같이 테스트해보았을 때, 물론 Singleton을 이용한 두 클래스를 먼저 준비했다는 전제하에, 두 클래스는 자원이 공유된 동일한 객체임을 확인해볼 수 있다!

한 번 더 확인해보자! 이번에는 Singleton 클래스의 접근을 public으로 변경하고

```java
package com.designPattern.generator.singleton;

public class Singleton {
    public Singleton(){

    }

    private static Singleton s;

    public static Singleton getInstance(){
       if(s==null){
           //생성자로 정의되어 있지 않은 경우[static은 this를
           // 사용할 수 없기 때문에 this.s는 안됨!!]
           s = new Singleton();
       }
       return s;
    }

    public void connect(){
        System.out.println("연결되었습니다");
    }

}
```

A클래스와 B클래스도 이에 따라 기본 생성자에서 Singleton 생성자를 이용한다면

```java
package com.designPattern.generator.singleton;

public class A {
    private Singleton socketClient;

    public A(){
   //     this.socketClient = Singleton.getInstance();
        this.socketClient= new Singleton();
    }

    public Singleton getSingleton() {
        return this.socketClient;
    }

}
```

```java
package com.designPattern.generator.singleton;

public class B {
    private Singleton socketClient;

    public B(){
        //this.socketClient = Singleton.getInstance();
        this.socketClient= new Singleton();
    }

    public Singleton getSingleton(){
        return this.socketClient;
    }
}
```

![https://github.com/hy6219/TIL-Today-I-Learned-/blob/main/Spring/%EB%94%94%EC%9E%90%EC%9D%B8%ED%8C%A8%ED%84%B4/%EC%83%9D%EC%84%B1%ED%8C%A8%ED%84%B4/%EC%8B%B1%EA%B8%80%ED%86%A4%ED%8C%A8%ED%84%B4/%EC%8B%B1%EA%B8%80%ED%86%A4%ED%8C%A8%ED%84%B4%EC%9D%98%20%EC%A0%91%EA%B7%BC%EC%9D%84%20%ED%92%80%EC%97%88%EC%9D%84%20%EA%B2%BD%EC%9A%B0.PNG?raw=true](https://github.com/hy6219/TIL-Today-I-Learned-/blob/main/Spring/%EB%94%94%EC%9E%90%EC%9D%B8%ED%8C%A8%ED%84%B4/%EC%83%9D%EC%84%B1%ED%8C%A8%ED%84%B4/%EC%8B%B1%EA%B8%80%ED%86%A4%ED%8C%A8%ED%84%B4/%EC%8B%B1%EA%B8%80%ED%86%A4%ED%8C%A8%ED%84%B4%EC%9D%98%20%EC%A0%91%EA%B7%BC%EC%9D%84%20%ED%92%80%EC%97%88%EC%9D%84%20%EA%B2%BD%EC%9A%B0.PNG?raw=true)

싱글톤패턴을 해제시켰을 때 동등성과 동일성

그 결과는 위와 같이 동등성도, 동일성도 지켜질 수 없음을 확인해볼 수 있다. 즉, 싱글톤패턴이 아니라면, 다른 객체로 인식될 수 있다라는 의미이다!
