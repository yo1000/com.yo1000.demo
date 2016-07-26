# Controller

コントローラには、`@Controller` アノテーションを付与し、API など REST インターフェースを扱うコントローラには、`@RestController` アノテーションを付与します。

## 命名

コントローラとはすなわち、リクエストとサービス、レスポンスのマッピングを表現するものです。

そのため、メソッドの命名には、それがどういうリクエストを処理するものなのかが、かんたんに把握できるような名前をつけるべきでしょう。より具体的には、HTTP メソッドと、リクエストしているアクションを組み合わせた名前とするのが良いです。

- `note` リソースへ、HTTP `GET` で、`/` をリクエスト : `get` または、`getIndex`
- `note` リソースへ、HTTP `GET` で、`/search` アクションをリクエスト : `getSearch`
- `note` リソースへ、HTTP `POST` で、`/` をリクエスト : `post` または、`postIndex`

# Service

サービスクラスには、`@Service` アノテーションを付与します。

## 命名

サービスとはすなわち、業務処理、プロセスを表現するものです。

そのため、メソッドの命名にはその業務処理に即した名前をつけてください。

今回は `Note` という、メモを取ったり（書いたり）、読んだりすることを主眼においた業務処理なので、以下のような命名となるでしょう。

- `Note`(Service) を 読む : `read`
- `Note`(Service) を 書く : `write`

# Repository

リポジトリクラスには、`@Repository` アノテーションを付与します。

## 命名

リポジトリとはすなわち、永続化を表現するものです。古くは DAO と表現されることが多かったかもしれませんが、データソースの種類が時代とともに増加したことに連れ、表現が変わってきているように感じます。

そのため、メソッドの命名には様々なデータソースにおいて、広く伝わる汎用的な名前をつけてください。

より具体的には JPA で採用されている命名規則に従うのが、ひとつのプラクティスといえるでしょう。

_Notice:_  
_対応しないものもあるので、まずは JPA ベースで対応する命名を探して、対応が存在しない事が判明した場合には、規則から外れた命名を認めます。_

今回は、`Note` というモデルに対する読み書きの操作なので、以下のような命名となるでしょう。

- 読み取り操作における JPA 命名: `find` / `findAll` / `findBy{ConditionColumn}`
- 書き込み操作における JPA 命名: `save`

# Model

モデルクラスには、とくにクラスアノテーションを付与しません。

MyBatis を使用する上では、`@Entity` アノテーションは不要です。もし、Hibernate の `auto-ddl` 機能を使用したい場合には、`@Entity` の使用を検討します。

# Transaction

トランザクション境界としたいサービスクラスのメソッドに、`＠Transactional` を設定することで、トランザクションを設定します。ログを確認することで、実際にトランザクションがどの範囲にかかっているのかが分かります。

## `@Transactional` なし

`NoteService#readAll()` の場合、トランザクションがかからないため、トランザクションマネージャーによる、ログの出力が発生しない。

```
2016-07-26 12:27:36.312  INFO 36652 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring FrameworkServlet 'dispatcherServlet'
2016-07-26 12:27:36.313  INFO 36652 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization started
2016-07-26 12:27:36.332  INFO 36652 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : FrameworkServlet 'dispatcherServlet': initialization completed in 19 ms
2016-07-26 12:27:36.376 DEBUG 36652 --- [nio-8080-exec-1] o.j.s.OpenEntityManagerInViewInterceptor : Opening JPA EntityManager in OpenEntityManagerInViewInterceptor
2016-07-26 12:27:36.758 DEBUG 36652 --- [nio-8080-exec-1] o.j.s.OpenEntityManagerInViewInterceptor : Closing JPA EntityManager in OpenEntityManagerInViewInterceptor
2016-07-26 12:27:36.759 DEBUG 36652 --- [nio-8080-exec-1] o.s.orm.jpa.EntityManagerFactoryUtils    : Closing JPA EntityManager
```

## `@Transactional` あり

`NoteService#write(Note)` の場合、トランザクションがかかっているため、トランザクションマネージャーによる、トランザクションの開始から、コミットに至るまでのログが出力される。

```
2016-07-26 12:27:47.962 DEBUG 36652 --- [nio-8080-exec-3] o.j.s.OpenEntityManagerInViewInterceptor : Opening JPA EntityManager in OpenEntityManagerInViewInterceptor
2016-07-26 12:27:47.976 DEBUG 36652 --- [nio-8080-exec-3] o.s.orm.jpa.JpaTransactionManager        : Found thread-bound EntityManager [org.hibernate.jpa.internal.EntityManagerImpl@6dca04ca] for JPA transaction
2016-07-26 12:27:47.976 DEBUG 36652 --- [nio-8080-exec-3] o.s.orm.jpa.JpaTransactionManager        : Creating new transaction with name [com.yo1000.demo.service.NoteService.write]: PROPAGATION_REQUIRED,ISOLATION_DEFAULT; ''
2016-07-26 12:27:47.978 DEBUG 36652 --- [nio-8080-exec-3] o.s.orm.jpa.JpaTransactionManager        : Exposing JPA transaction as JDBC transaction [org.springframework.orm.jpa.vendor.HibernateJpaDialect$HibernateConnectionHandle@9d4ae70]
2016-07-26 12:27:47.986 DEBUG 36652 --- [nio-8080-exec-3] o.s.orm.jpa.JpaTransactionManager        : Initiating transaction commit
2016-07-26 12:27:47.986 DEBUG 36652 --- [nio-8080-exec-3] o.s.orm.jpa.JpaTransactionManager        : Committing JPA transaction on EntityManager [org.hibernate.jpa.internal.EntityManagerImpl@6dca04ca]
2016-07-26 12:27:47.988 DEBUG 36652 --- [nio-8080-exec-3] o.s.orm.jpa.JpaTransactionManager        : Not closing pre-bound JPA EntityManager after transaction
2016-07-26 12:27:47.993 DEBUG 36652 --- [nio-8080-exec-3] o.j.s.OpenEntityManagerInViewInterceptor : Closing JPA EntityManager in OpenEntityManagerInViewInterceptor
2016-07-26 12:27:47.993 DEBUG 36652 --- [nio-8080-exec-3] o.s.orm.jpa.EntityManagerFactoryUtils    : Closing JPA EntityManager
```

# MyBatis Mapper XML (SQL)

MyBatis では、SQL を XML で管理します。Mapper XML という名前の通り、この XML で、クエリとパラメーター、およびレスポンスの O/R Mapping を管理しています。

XML 中で、とくに重要となるのが以下の2点で、これらはプロジェクトの構成に関連を持ちます。

- `mapper[namespace]`
- `select[id]` `insert[id]` `update[id]` `delete[id]` `sql[id]`

関連を持つのは、先の説明に出てきたリポジトリで、このクラスの FQCN (完全修飾クラス名)を `mapper[namespace]` と一致させ、メソッド名を `[id]` と一致させることで、リポジトリは、インターフェース定義のみで、実装を必要とすることなく、作成を完了することができるようになります。

より具体的な例については、以下を参考にしてください。

- `src/main/java/com/yo1000/demo/repository/NoteRepository.java`
- `src/main/resources/mybatis/sql/NoteRepository.xml`
