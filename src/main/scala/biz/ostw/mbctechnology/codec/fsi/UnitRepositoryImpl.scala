package biz.ostw.mbctechnology.codec.fsi

class UnitRepositoryImpl extends UnitRepository {

  override def units(): Array[Unit] = {
    Array(
      Unit("10^9/L"),
      Unit("10^12/L"),
      Unit("g/L"),
      Unit("fL"),
      Unit("pg"),
      Unit("%"),
    )
  }
}
