import 'package:flutter/material.dart';
import 'package:gogo_food/viewmodels/search_view_model.dart';
import 'package:provider/provider.dart';

class SearchResultScreen extends StatelessWidget {
  final String query;
  const SearchResultScreen({super.key, required this.query});

  @override
  Widget build(BuildContext context) {
    final vm = context.watch<SearchViewModel>();

    return Scaffold(
      appBar: AppBar(
        title: Text('Result for "$query"'),
        backgroundColor: Colors.redAccent,
      ),
      body: vm.isLoading
          ? const Center(child: CircularProgressIndicator())
          : ListView.builder(
              itemBuilder: (context, index) {
                final item = vm.results[index];
                return ListTile(
                  leading: Image.network(
                    item['imageUrl'],
                    width: 50,
                    height: 50,
                    fit: BoxFit.cover,
                  ),
                  title: Text(item['name']),
                  subtitle: Text(
                    '${item['distance']} km • ${item['rating']} ★',
                  ),
                );
              },
            ),
    );
  }
}
