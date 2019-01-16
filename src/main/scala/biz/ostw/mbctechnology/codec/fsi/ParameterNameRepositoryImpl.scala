package biz.ostw.mbctechnology.codec.fsi

class ParameterNameRepositoryImpl extends ParameterNameRepository {

  override def names(): Array[ParameterName] = {
    Array(
      ParameterName("wbc"),
      ParameterName("neu_num"),
      ParameterName("lym_num"),
      ParameterName("mon_num"),
      ParameterName("eos_num"),
      ParameterName("bas_num"),
      ParameterName("neu_per"),
      ParameterName("lym_per"),
      ParameterName("mon_per"),
      ParameterName("eos_per"),
      ParameterName("bas_per"),
      ParameterName("rbc"),
      ParameterName("hgb"),
      ParameterName("hct"),
      ParameterName("mcv"),
      ParameterName("mch"),
      ParameterName("mchc"),
      ParameterName("rdw_cv"),
      ParameterName("rdw_sd"),
      ParameterName("plt"),
      ParameterName("mpv"),
      ParameterName("pwd"),
      ParameterName("pct")
    )
  }
}
