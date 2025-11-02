## 🌱 Spring Framework 요약

### 📚 Framework란?
- **라이브러리**: 개발자가 작성한 코드 파일  
- **API**: 여러 라이브러리가 모인 패키지 (JAR 형태)  
- **프레임워크(Framework)**: 다수의 API가 모여 거대한 개발 구조를 형성한 것  

---

### 💡 Framework의 장점
- 이미 **개발 구조가 정의되어 있어**, 실력이 부족해도 기본 틀이 보장됨  
- **일정한 품질의 결과물** 확보 가능  
- **개발 시간 단축** 및 **유지보수 용이성** 향상  
- **조립형 개발 구조**로 협업에 유리  

---

## ☕ Spring Framework

### ⚙️ 개요
- **경량 프레임워크(Lightweight Framework)**  
- 복잡한 기능 대신 필요한 기능만 선택적으로 사용  
- **모듈형 구조**, **유연한 설정**, **생산성 높은 개발환경** 제공  

---

### 🌼 주요 특징
| 특징 | 설명 |
|------|------|
| **POJO 기반 구성** | 순수 Java 객체로 간단히 구성 가능 (Plain Old Java Object) |
| **AOP 지원** | 횡단 관심사(보안, 로그, 트랜잭션 등)를 분리하여 핵심 로직 집중 가능 |
| **Transaction 관리** | 어노테이션 기반 자동 커밋/롤백 관리 |
| **편리한 MVC 구조** | Controller-Model-View 구조로 분리된 설계 |
| **WAS 비종속적 환경** | 단위 테스트 및 독립 실행이 용이 |
| **DI(Dependency Injection)** | 객체 간의 의존성을 외부에서 주입받아 유연한 관계 구성 가능 |

---

### 🧩 POJO (Plain Old Java Object)
- 특별한 규칙 없이 **순수 Java 코드로 객체 구성 가능**  
- 복잡한 상속이나 인터페이스 구현 없이도 스프링에서 관리 가능  

---

### 🎯 AOP (Aspect Oriented Programming)
- **관점 지향 프로그래밍**  
- 핵심 로직과 부가 로직(보안, 로그, 예외 처리 등)을 분리  
- **횡단 관심사(Cross-cutting concern)**를 모듈로 분리해 재사용성 향상  
- 서비스 로직(종단 관심사)에만 집중할 수 있는 구조 제공  

---

### 💾 Transaction 관리
- 어노테이션(`@Transactional`)으로 트랜잭션 영역을 지정  
- 예외 발생 시 **자동 롤백**, 정상 수행 시 **자동 커밋** 처리  

---

### 🧱 WAS 비종속적 개발
- 전체 애플리케이션 실행 없이 **단위 테스트(Unit Test)** 가능  
- 개발 및 디버깅 효율성 증가  

---

### 🧶 DI (Dependency Injection, 의존성 주입)
| 구분 | 설명 |
|------|------|
| **의존성(Dependency)** | A 객체가 B 객체 없이는 동작할 수 없는 상태 |
| **주입(Injection)** | 외부에서 필요한 객체를 밀어 넣는 방식 |

📘 예시  
- 기존: A 내부에서 직접 B 생성 → **강한 결합**  
- DI 방식: A가 B 필요 신호만 보냄 → **ApplicationContext**가 주입 (약한 결합)

> 💡 ApplicationContext가 관리하는 객체를 **Bean(빈)** 이라 하며,  
> 이 Bean들이 저장되는 공간을 **Spring Container(Bean Container)** 라고 한다.

---

## 🚀 Spring Boot
- **Spring Framework의 설정 자동화 버전**  
- 복잡한 XML 설정, 라이브러리 버전 호환 문제 해결  
- 필요한 라이브러리를 자동 구성 및 관리  

---

## 📁 프로젝트 기본 경로 구조
| 경로 | 설명 |
|------|------|
| `src/main/java` | 서버단 Java 파일 |
| `src/test/java` | 단위 테스트용 Java 파일 |
| `src/main/resources` | 설정 파일 및 뷰 파일 |
| `src/main/resources/static` | 정적 파일 (CSS, JS, Image 등) |
| `src/main/resources/templates` | HTML 템플릿 파일 |
| `build.gradle` | 라이브러리 및 종속성 관리 |
| `application.yml` | Spring 설정 파일 (환경 설정) |

---

## 🔧 의존성 주입 실습
- **Food**, **Knife** 두 클래스를 구성  
- DI를 통해 객체 간 관계를 설정하고 단위 테스트 수행  

---

### 🏷️ @Qualifier
- `@Autowired` 로 객체를 주입할 때, **같은 타입이 여러 개 있으면 구분 불가**  
- `@Qualifier("식별자")` 를 사용하여 원하는 Bean을 선택적으로 주입 가능  

📘 실습 예시
- `Restaurant`, `Outback`, `Vips` 클래스 생성  
- `Restaurant` 인터페이스 구현, 각 클래스별로  
  - 셀프바 이용 여부  
  - 스테이크 가격 필드 설정  
- 기본 레스토랑은 `Outback` 으로 설정  

---

## 🧭 Spring MVC (Front Controller Pattern)

```
            HandlerMapping
 REQUEST       ①        ②↕         ③       ④
     ]    ↔ DispatcherServlet ↔ HandlerAdapter ↔ Controller
 RESPONSE      ⑦    ⑥↕        ⑤↕
             View  ↔  ViewResolver
                  ↕
                HTML
```

### ⚙️ Spring MVC 특징
- `HttpServletRequest`, `HttpServletResponse` 직접 사용 X  
- 다양한 파라미터/리턴 타입 처리 가능  
- 전송 방식(GET/POST 등)을 **어노테이션으로 처리**  
- 상속이나 인터페이스 없이도 **어노테이션 기반 설정만으로 구성 가능**

---

## 🌐 REST (Representational State Transfer)
- **URI만으로도 리소스(데이터, 행위)를 이해할 수 있도록 설계된 통신 규칙**

### 🧱 RESTful 설계 원칙
1. **소문자 사용** – 대문자는 오류 유발 가능  
2. **언더바(_) 대신 하이픈(-)** – 가독성 향상  
3. **마지막 슬래시(/)** – 작성하지 않는다  
4. **계층 관계**는 슬래시(`/`)로 구분  
5. **파일 확장자**는 포함하지 않는다 (`Content-Type`으로 처리)  
6. **데이터는 명사**, **행위는 동사로 표현**  
   ```
   ❌ /members/get/1
   ✅ /members/delete/1
   ```
7. **리소스 이름은 복수형으로 작성**  
   ```
   /members, /products, /orders
   ```

---

## 🧩 3-Tier 아키텍처

| 계층 | 이름 | 역할 |
|------|------|------|
| **Presentation Tier** | 화면 계층 | 화면 표시, 요청 수신 (Controller) |
| **Business Tier** | 비즈니스 계층 | 핵심 로직 수행 (Service) |
| **Persistence Tier** | 영속 계층 | DB 접근 및 저장 로직 (Mapper, Repository) |

### 🎯 3-Tier 목적
- **각 계층의 독립성 보장**  
- 변경 발생 시 필요한 부분만 교체 가능 (유지보수성 향상)  

### 🧭 구조 흐름
```
Presentation ↔ Business ↔ Persistence ↔ DBMS
   ↑              ↑         ↑          ↑
Controller     Service   Repository   Mapper
```
