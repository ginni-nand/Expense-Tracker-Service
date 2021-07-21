package normfile

import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.Int
import kotlin.String
import kotlin.Unit
import norm.ParamSetter
import norm.Query
import norm.RowMapper

public data class AddUserParams(
  public val name: String?,
  public val age: Int?,
  public val email: String?,
  public val password: String?
)

public class AddUserParamSetter : ParamSetter<AddUserParams> {
  public override fun map(ps: PreparedStatement, params: AddUserParams): Unit {
    ps.setObject(1, params.name)
    ps.setObject(2, params.age)
    ps.setObject(3, params.email)
    ps.setObject(4, params.password)
  }
}

public data class AddUserResult(
  public val id: Int,
  public val name: String?,
  public val age: Int?,
  public val email: String?,
  public val password: String?
)

public class AddUserRowMapper : RowMapper<AddUserResult> {
  public override fun map(rs: ResultSet): AddUserResult = AddUserResult(
  id = rs.getObject("id") as kotlin.Int,
    name = rs.getObject("name") as kotlin.String?,
    age = rs.getObject("age") as kotlin.Int?,
    email = rs.getObject("email") as kotlin.String?,
    password = rs.getObject("password") as kotlin.String?)
}

public class AddUserQuery : Query<AddUserParams, AddUserResult> {
  public override val sql: String =
      "INSERT INTO users(name,age,email,password) VALUES(?,?,?,?) RETURNING *;"

  public override val mapper: RowMapper<AddUserResult> = AddUserRowMapper()

  public override val paramSetter: ParamSetter<AddUserParams> = AddUserParamSetter()
}
