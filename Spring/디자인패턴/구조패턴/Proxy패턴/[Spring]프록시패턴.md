# 디자인 패턴3-프록시 패턴

- 디자인 패턴 중 구조 패턴의 일종!!
- Proxy==대리인!
- 어떤 것을 대신해서 처리해주는 역할을 수행함
- SOLID 중 개방폐쇄 원칙OCP와 의존역전원칙DIP을 따름
- Proxy 클래스를 통해서 대신 작업을 전달해주는 형태로 설계됨
- 실제 클라이언트는 프록시로부터 결과를 받게 됨
- 예시1 : 스프링의 AOP에서 사용되는 패턴
- 예시2: 캐시(이미 받아둔 결과가 있다면 변화가 없는 한, 그대로 클라이언트에게 넘겨주는 기능)
- 흩어진 기능을 모아주거나, 공통된 기능을 묶어주는 기능을 수행할 수도 있음
- 서버 내 어떤 기능으로 인해 성능이 저하되는 지 찾아낼 때에도 사용됨

![https://github.com/hy6219/TIL-Today-I-Learned-/blob/main/Spring/%EB%94%94%EC%9E%90%EC%9D%B8%ED%8C%A8%ED%84%B4/%EA%B5%AC%EC%A1%B0%ED%8C%A8%ED%84%B4/Proxy%ED%8C%A8%ED%84%B4/%ED%94%84%EB%A1%9D%EC%8B%9C%ED%8C%A8%ED%84%B4.png?raw=true](https://github.com/hy6219/TIL-Today-I-Learned-/blob/main/Spring/%EB%94%94%EC%9E%90%EC%9D%B8%ED%8C%A8%ED%84%B4/%EA%B5%AC%EC%A1%B0%ED%8C%A8%ED%84%B4/Proxy%ED%8C%A8%ED%84%B4/%ED%94%84%EB%A1%9D%EC%8B%9C%ED%8C%A8%ED%84%B4.png?raw=true)

프록시패턴

브라우저의 캐싱기능을 예로 들어서 연습해보자

먼저 브라우저는 Html문서를 로딩받아 보여주기 때문에 이러한 기능을 갖는 인터페이스인 IBrowser를 아래와 같이 작성해보자

```java
package com.designPattern.structure.proxy;

public interface IBrowser {
    Html show();
}
```

그리고 이를 구현한 클래스인 BrowserProxy를 만들어보도록 하는데, 매번 문서를 로딩하는 것은 비효율적이기 때문에, 처음 한 번은 로딩하고 그 이후에는 저장된 문서를 불러오도록 하자

그렇게 될 경우,

필요한 필드는 "로딩할 html문서가 있는 url"과 "html문서를 의미하는 필드"가 있을 것이다!

그리고, html이 비어있는지에 따라 작동하도록 메서드를 오버라이딩해주어야 할 것이다

그러면 html클래스를 먼저 작성해보자

```java
package com.designPattern.structure.proxy;

public class Html {
    private String url;
    //url을 받아와서 html을 로딩
    public Html(String url){
        this.url=url;
    }
}
```

그리고 이러한 Html문서를 이용한 프록시 클래스를 만들어주자

```java
package com.designPattern.structure.proxy;

public class BrowserProxy implements IBrowser{
    //캐싱을 위해서!
    private String url;
    private Html html;

    public BrowserProxy(String url){
        this.url= url;
    }

    @Override
    public Html show() {
        if(html==null){
            this.html=new Html(url);//비어있다면 새로내어주고
            System.out.println("BrowserProxy loading html from "+url);
        }
        System.out.println("BrowserProxy use cache "+ url);
        return html;//그게 아니라면 자기 자신이 갖고 있는 url을 리턴
    }
}
```

위의 show메서드를 보면, html 필드가 비어져 있는 경우에는 Html에게 html객체 인스턴스 생성을 위임하여 로딩하도록 함을 확인해볼 수 있다

그 결과, 클라이언트인 Main에서 확인해보면

```java
package com.designPattern.structure.proxy;

public class ProxyMain {
    public static void main(String[] args){
        IBrowser iBrowser = new BrowserProxy("http://www.naver.com");
        iBrowser.show();//BrowserProxy loading html from http://www.naver.com
        iBrowser.show();//BrowserProxy use cache http://www.naver.com
        iBrowser.show();//BrowserProxy use cache http://www.naver.com
        iBrowser.show();//BrowserProxy use cache http://www.naver.com
        iBrowser.show();//BrowserProxy use cache http://www.naver.com

    }
}
```

처음 한번만 로딩되고 그 이후에는 캐싱되는 것을 확인해볼 수 있다!

이러한 프록시패턴은 어댑터패턴과 비교해보면, 연관성이 다소 떨어지는 클래스 혹은 인터페이스를 연결해준다는 점이 논리적으로 유사한 것 같다!
