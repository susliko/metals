package tests.pc

import tests.BaseCompletionSuite

class CompletionScalaCliSuite extends BaseCompletionSuite {
  check(
    "simple",
    """|//> using lib "io.cir@@
       |package A
       |""".stripMargin,
    "io.circe",
  )

  checkEdit(
    "multiple-deps",
    """|// Multiple using lib
       |//> using lib ???
       |// //> using lib ???
       |//> using lib io.circe::circe-core_na@@
       |package A
       |""".stripMargin,
    """|// Multiple using lib
       |//> using lib ???
       |// //> using lib ???
       |//> using lib io.circe::circe-core_native0.4
       |package A
       |""".stripMargin,
  )

  check(
    "single-colon",
    """|//> using lib "io.circe:circe-core_na@@
       |package A
       |""".stripMargin,
    """|circe-core_native0.4_2.12
       |circe-core_native0.4_2.13
       |circe-core_native0.4_3
       |""".stripMargin,
  )

  check(
    "version",
    """|//> using lib "io.circe::circe-core_native0.4:@@"
       |package A
       |""".stripMargin,
    "0.14.3",
  )

  check(
    "multiple-libs",
    """|//> using lib "io.circe::circe-core:0.14.0", "io.circe::circe-core_na@@"
       |package A
       |""".stripMargin,
    "circe-core_native0.4",
  )

  check(
    "script",
    """|//> using lib "io.circe:circe-core_na@@
       |package A
       |""".stripMargin,
    """|circe-core_native0.4_2.12
       |circe-core_native0.4_2.13
       |circe-core_native0.4_3
       |""".stripMargin,
    filename = "script.sc",
  )

}
