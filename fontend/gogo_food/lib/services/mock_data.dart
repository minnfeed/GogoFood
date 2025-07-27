import 'package:gogo_food/models/category.dart';
import 'package:gogo_food/models/dish.dart';

final List<Category> mockCategories = [
  Category(title: "Flash deal", imagePath: "assets/icons/flash_deal.png"),
  Category(title: "Noodles", imagePath: "assets/icons/noodles.png"),
  Category(title: "Rice", imagePath: "assets/icons/rice.png"),
  Category(title: "Bread", imagePath: "assets/icons/bread.png"),
  Category(title: "Pizza", imagePath: "assets/icons/pizza.png"),
  Category(title: "Dessert", imagePath: "assets/icons/dessert.png"),
  Category(title: "Drink", imagePath: "assets/icons/drink.png"),
  Category(title: "Other", imagePath: "assets/other.png"),
];

final List<Map<String, dynamic>> allCategories = [
  {'icon': 'assets/icons/flash_deal.png', 'label': 'Flash deal'},
  {'icon': 'assets/icons/noodles.png', 'label': 'Noodles'},
  {'icon': 'assets/icons/rice.png', 'label': 'Rice'},
  {'icon': 'assets/icons/bread.png', 'label': 'Bread'},
  {'icon': 'assets/icons/pizza.png', 'label': 'Pizza'},
  {'icon': 'assets/icons/dessert.png', 'label': 'Dessert'},
  {'icon': 'assets/icons/drink.png', 'label': 'Drink'},
  {'icon': 'assets/icons/other.png', 'label': 'Other'},
];

final List<DishCategory> mockDishes = [
  DishCategory(
    categoryTitle: 'Flash deal',
    dishes: [
      Dish(
        name: 'Deal Combo 1',
        imagePath: 'assets/images/tra_dau.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Deal Combo 2',
        imagePath: 'assets/images/milo_dam.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Deal Combo 3',
        imagePath: 'assets/images/soda_viet_quat.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
    ],
  ),
  DishCategory(
    categoryTitle: 'Noodles',
    dishes: [
      Dish(
        name: 'Hủ tiếu',
        imagePath: 'assets/images/tra_dau.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Phở bò',
        imagePath: 'assets/images/milo_dam.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Bún bò Huế',
        imagePath: 'assets/images/soda_viet_quat.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
    ],
  ),
  DishCategory(
    categoryTitle: 'Rice',
    dishes: [
      Dish(
        name: 'Cơm gà',
        imagePath: 'assets/images/tra_dau.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Cơm tấm',
        imagePath: 'assets/images/milo_dam.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Cơm chiên',
        imagePath: 'assets/images/soda_viet_quat.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
    ],
  ),
  DishCategory(
    categoryTitle: 'Bread',
    dishes: [
      Dish(
        name: 'Bánh mì thịt',
        imagePath: 'assets/images/tra_dau.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Bánh mì ốp la',
        imagePath: 'assets/images/milo_dam.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Bánh mì chả cá',
        imagePath: 'assets/images/soda_viet_quat.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
    ],
  ),
  DishCategory(
    categoryTitle: 'Pizza',
    dishes: [
      Dish(
        name: 'Pizza hải sản',
        imagePath: 'assets/images/tra_dau.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Pizza bò',
        imagePath: 'assets/images/milo_dam.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Pizza phô mai',
        imagePath: 'assets/images/soda_viet_quat.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
    ],
  ),
  DishCategory(
    categoryTitle: 'Dessert',
    dishes: [
      Dish(
        name: 'Bánh Flan',
        imagePath: 'assets/images/tra_dau.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Bánh Croissant',
        imagePath: 'assets/images/milo_dam.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Bánh Cua Phô mai',
        imagePath: 'assets/images/soda_viet_quat.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
    ],
  ),
  DishCategory(
    categoryTitle: 'Drink',
    dishes: [
      Dish(
        name: 'Trà dâu',
        imagePath: 'assets/images/tra_dau.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Milo dầm',
        imagePath: 'assets/images/milo_dam.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Soda việt quất',
        imagePath: 'assets/images/soda_viet_quat.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Trà chanh',
        imagePath: 'assets/images/tra_chanh.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Dưa hấu ép',
        imagePath: 'assets/images/dua_hau.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
      Dish(
        name: 'Cam ép',
        imagePath: 'assets/images/cam_ep.jpg',
        rating: 4.8,
        reviewCount: 1200,
        isFavourite: true,
      ),
    ],
  ),
];
