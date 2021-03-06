# CSS-선언하는 방식

1. 태그에 직접 작성하기(인라인)

```html
<div style="color:red;">태그에 직접 작성! 인라인 방식!</div>

<태그 style="속성:값;"></태그>
<!--선택자가 생략되어 있는 이유: 이미 이 태그, 이 부분에 적용할 것임이
   명백하기 때문!-->
```

- 단점: 1) 직접 태그를 찾아서 속성과 값을 넣어야 하는데, 
          이 방식이 같은 스타일이 적용되는 경우, 일일히 다 적어주어야 함

         2) 더 나아가서, 스타일 수정 시 불편함!
- 자바스크립트가 html 파일 내에 css 를 강제로 삽입할 경우에는 인라인 방식을 사용할 수 있지만(코드로 일괄 적용이 가능하기 때문!), 그 외에는 권장하지 않음!

2.  HTML에 포함하기(내장)-embed 방식(embedded)

```html
<head>
<!--정보 범위 설정-->
	<style>
<!--스타일 내장-->
		div{
			color:red;
		}
	</style>
</head>
<body>
<!--구조 범위 설정-->
	<div>HTML에 포함1</div>
</body>
```

- 선택자를 이용할 경우, 원하는 파트에 해당 스타일을 모두 적용이 가능
( ➡️ 인라인 방식과의 차이점! )
(html 문서와 css 파일이 분리되어 있기 때문에, html 코드 부분만 보면 어떤 스타일이 적용되었는지는 알 수 없지만, script 태그로 둘러싸인 부분으로 스타일이 적용됨!)

3. HTML 외부에서 CSS 불러오기

CSS 코드를 HTML 문서와 완전히 분리된 상태로 보관하고, 이를 link 태그를 통해서 불러와서 사용

html1.html

```html
<head>
	<link rel="stylesheet" href="/css/main.css"><!--절대경로-->
<!--경로 개념!-->
</head>
<body>
	<div>HTML에 외부에서 불러오기1</div>
</body>
```

html2.html

```html
<head>
	<link rel="stylesheet" href="/css/main.css"><!--절대경로-->
</head>
<body>
	<div>HTML에 외부에서 불러오기2</div>
</body>
```

main.css

```css
div{
	color: red;
}
```

➡️ 장점!!

- 다양한 html 파일에 하나의 css 파일로 적용할 수 있음

✅위의 예처럼, html1.html과 html2.html에 main.css를 적용하면,

두 개의 html 파일 모두에 동일한 스타일을 적용할 수 있음

🌟 경로 개념 (나중에 더 확장!)🌟

- 상대 경로
- 절대 경로 : href="/css/main.css" 와 같은 방식
