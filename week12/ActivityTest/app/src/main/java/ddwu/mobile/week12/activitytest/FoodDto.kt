package ddwu.mobile.week12.activitytest

import java.io.Serializable

data class FoodDto(val photo: Int, val food: String, var count: Int) : Serializable
