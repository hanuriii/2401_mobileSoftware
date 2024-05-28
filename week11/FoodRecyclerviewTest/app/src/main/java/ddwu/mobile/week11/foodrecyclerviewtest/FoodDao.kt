package ddwu.mobile.week11.foodrecyclerviewtest

class FoodDao {
    val foods = ArrayList<FoodDto>()    //FoodDto를 하나씩 담는 리스트 foods 생성

    init {
        foods.add(FoodDto(R.drawable.food01, "치즈", 10))
        foods.add(FoodDto(R.drawable.food02, "치킨", 5))
        foods.add(FoodDto(R.drawable.food03, "도넛", 15))
        foods.add(FoodDto(R.drawable.food04, "사과", 20))
        foods.add(FoodDto(R.drawable.food05, "핫도그", 3))
        foods.add(FoodDto(R.drawable.food06, "파스타", 5))
        foods.add(FoodDto(R.drawable.food07, "아이스크림", 15))
    }
}