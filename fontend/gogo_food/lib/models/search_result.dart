class SearchResult {
  final String name;
  final String image;
  final double rating;

  SearchResult({required this.name, required this.image, required this.rating});

  factory SearchResult.fromJson(Map<String, dynamic> json) {
    return SearchResult(
      name: json['name'],
      image: json['image'],
      rating: json['rating'].toDouble(),
    );
  }
}
