class Dish {
  final String name;
  final String imagePath;
  final double rating;
  final int reviewCount;
  final bool isFavourite;

  Dish({
    required this.name,
    required this.imagePath,
    required this.rating,
    required this.reviewCount,
    required this.isFavourite,
  });
}

class DishCategory {
  final String categoryTitle;
  final List<Dish> dishes;

  DishCategory({required this.categoryTitle, required this.dishes});
}
