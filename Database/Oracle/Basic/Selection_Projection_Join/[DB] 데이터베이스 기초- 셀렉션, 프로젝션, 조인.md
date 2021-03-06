﻿# 셀렉션(Selection)

- 셀렉션은 쉽게 말하면, 행단위의 데이터 조회를 의미!

- DB에서는 행단위의 데이터 조회가 열단위보다 우선시됨

![셀렉션](https://github.com/hy6219/TIL-Today-I-Learned-/blob/main/Database/Oracle/Basic/Selection_Projection_Join/%EC%85%80%EB%A0%89%EC%85%98.png?raw=true)

# 프로젝션(Projection)

- 프로젝션은 쉽게 말하면, 열(컬럼) 단위의 데이터 조회를 의미

![프로젝션](https://github.com/hy6219/TIL-Today-I-Learned-/blob/main/Database/Oracle/Basic/Selection_Projection_Join/%ED%94%84%EB%A1%9C%EC%A0%9D%EC%85%98.png?raw=true)

# 셀렉션 + 프로젝션

- 행과 열을 적절하게 섞어서 조회하는 경우이다!

- 모든 행에 대해서 특정 조건 성립 시 찾는 방식이라고 생각하면 될 것


![셀렉션+프로젝션](https://github.com/hy6219/TIL-Today-I-Learned-/blob/main/Database/Oracle/Basic/Selection_Projection_Join/%EC%85%80%EB%A0%89%EC%85%98+%ED%94%84%EB%A1%9C%EC%A0%9D%EC%85%98.png?raw=true)

# 조인

- 다른 두 테이블이, 어떠한 한 컬럼에 대해서 동일한 맥락/값을 갖고 있을 때 **합치게** 되어 하나의 테이블로 만드는 개념
- 두 테이블 중 널값이 섞여 있고, 다른 테이블에 그에 해당되는 값 범주가 없으면 합칠때 NULL인 행은 빠지게 될 수 있음

![조인](https://github.com/hy6219/TIL-Today-I-Learned-/blob/main/Database/Oracle/Basic/Selection_Projection_Join/%EC%A1%B0%EC%9D%B8.png?raw=true)
