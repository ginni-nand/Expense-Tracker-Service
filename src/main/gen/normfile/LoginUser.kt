package normfile

import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.Int
import kotlin.String
import kotlin.Unit
import norm.ParamSetter
import norm.Query
import norm.RowMapper

public data class LoginUserParams(
  public val email: String?
)

public class LoginUserParamSetter : ParamSetter<LoginUserParams> {
  public override fun map(ps: PreparedStatement, params: LoginUserParams): Unit {
    ps.setObject(1, params.email)
  }
}

public data class LoginUserResult(
  public val id: Int,
  public val name: String?,
  public val age: Int?,
  public val email: String?,
  public val password: String?
)

public class LoginUserRowMapper : RowMapper<LoginUserResult> {
  public override fun map(rs: ResultSet): LoginUserResult = LoginUserResult(
  id = rs.getObject("id") as kotlin.Int,
    name = rs.getObject("name") as kotlin.String?,
    age = rs.getObject("age") as kotlin.Int?,
    email = rs.getObject("email") as kotlin.String?,
    password = rs.getObject("password") as kotlin.String?)
}

public class LoginUserQuery : Query<LoginUserParams, LoginUserResult> {
  public override val sql: String = "select * from users where email=?"

  public override val mapper: RowMapper<LoginUserResult> = LoginUserRowMapper()

  public override val paramSetter: ParamSetter<LoginUserParams> = LoginUserParamSetter()
}
