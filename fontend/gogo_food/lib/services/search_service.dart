import 'dart:convert';

import 'package:gogo_food/models/search_result.dart';
import 'package:http/http.dart' as http show get;

class SearchService {
  Future<List<SearchResult>> search(String query) async {
    final respone = await http.get(
      Uri.parse('https://yourapi.com/search?query=$query'),
    );

    if (respone.statusCode == 200) {
      final data = jsonDecode(respone.body);
      return List<SearchResult>.from(
        data.map((json) => SearchResult.fromJson(json)),
      );
    } else {
      throw Exception('Failed to load search results');
    }
  }
}
