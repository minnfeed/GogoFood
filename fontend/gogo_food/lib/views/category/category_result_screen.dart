import 'package:flutter/material.dart';
import 'package:gogo_food/models/dish.dart';
import 'package:gogo_food/viewmodels/dish_view_model.dart';
import 'package:google_fonts/google_fonts.dart';
import 'package:provider/provider.dart';

class CategoryResultScreen extends StatelessWidget {
  final String categoryTitle;
  final List<Dish> dishes;

  const CategoryResultScreen({
    super.key,
    required this.categoryTitle,
    required this.dishes,
  });

  @override
  Widget build(BuildContext context) {
    final dishVM = Provider.of<DishViewModel>(context, listen: true);
    return Scaffold(
      appBar: AppBar(title: Text(categoryTitle)),
      body: GridView.builder(
        padding: const EdgeInsets.all(16),
        itemCount: dishVM.currentDishes.length,
        gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
          mainAxisSpacing: 12,
          crossAxisSpacing: 12,
          childAspectRatio: 0.75,
        ),
        itemBuilder: (context, index) {
          final dish = dishVM.currentDishes[index];
          return Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              ClipRRect(
                borderRadius: BorderRadius.circular(12),
                child: Image.asset(
                  dish.imagePath,
                  height: 100,
                  width: double.infinity,
                  fit: BoxFit.cover,
                ),
              ),
              const SizedBox(height: 8),
              Text(
                dish.name,
                style: GoogleFonts.inter(fontWeight: FontWeight.bold),
              ),
              Text('⭐ ${dish.rating} (${_formatReviews(dish.reviewCount)})'),
            ],
          );
        },
      ),
    );
  }

  String _formatReviews(int count) {
    return count >= 1000 ? "${(count / 1000).toStringAsFixed(1)}k" : "$count";
  }
}
