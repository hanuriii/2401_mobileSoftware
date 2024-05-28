package ddwu.mobile.week11.foodrecyclerviewtest

data class FoodDto(val photo: Int, val food: String, var count: Int) {
//    override fun toString() : String {
//        return "$food ($count)"
//    }
    //4-6줄을 한줄로 표현
    override fun toString() = "$food ($count)"
}