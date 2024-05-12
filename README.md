<h2>Restaurant web app</h2>

This is a web restaurant app, which allows to:

- show all existing restaurants @GetMapping("/restaurants")
- show specific restaurant by its ID @GetMapping("/restaurants/{id}")
- add restaurant to the repository @PostMapping("/addrestaurant")
- delete restaurant from the repository @DeleteMapping("/restaurants/{id}")
- add meal to the specific restaurant @PostMapping("/restaurants/{restaurantId}/meals")
- delete meal from the specific restaurant @DeleteMapping("/restaurants/{restaurantId}/meals/{mealId}")
