package com.example.mycity.data

import com.example.mycity.model.Location

object DataSource {
    private val locations = listOf(
        Location(
            name = "The Daily Grind",
            description = "Cozy spot known for artisanal lattes and strong Wi-Fi.",
            category = "Coffee shops"
        ),
        Location(
            name = "The Blue Mug",
            description = "Local favorite for cold brew and vegan pastries.",
            category = "Coffee shops"
        ),
        Location(
            name = "Bean There Done That",
            description = "Minimalist cafe specializing in single-origin pour-overs.",
            category = "Coffee shops"
        ),
        Location(
            name = "Espresso Lane",
            description = "Quick service coffee kiosk with excellent breakfast sandwiches.",
            category = "Coffee shops"
        ),
        Location(
            name = "The Roastery",
            description = "A spacious spot where they roast their own beans on-site.",
            category = "Coffee shops"
        ),

        Location(
            name = "La Trattoria",
            description = "Authentic Italian restaurant with a family-style dining experience.",
            category = "Restaurants"
        ),
        Location(
            name = "Burger Haven",
            description = "Fast-casual spot famous for customizable gourmet burgers.",
            category = "Restaurants"
        ),
        Location(
            name = "Sushi Zen",
            description = "High-end Japanese eatery known for fresh nigiri and sashimi.",
            category = "Restaurants"
        ),
        Location(
            name = "El Fuego Mexican Grill",
            description = "Vibrant spot serving traditional tacos and margaritas.",
            category = "Restaurants"
        ),
        Location(
            name = "The Golden Spoon Diner",
            description = "Classic American diner open 24/7.",
            category = "Restaurants"
        ),
        Location(
            name = "Central Park Playground",
            description = "Fenced-in area with swings, slides, and soft rubber surfacing.",
            category = "Kid-friendly places"
        ),
        Location(
            name = "Splash Pad Zone",
            description = "Outdoor water play area, operational during summer months.",
            category = "Kid-friendly places"
        ),
        Location(
            name = "Little Explorers Museum",
            description = "Interactive children's museum focusing on science and art.",
            category = "Kid-friendly places"
        ),
        Location(
            name = "Giggles Indoor Playland",
            description = "Large soft-play structure and ball pit for various ages.",
            category = "Kid-friendly places"
        ),
        Location(
            name = "The Storybook Nook",
            description = "A bookstore with a dedicated, well-stocked children's reading area.",
            category = "Kid-friendly places"
        ),
        Location(
            name = "Oakwood Preserve",
            description = "Large, natural park with walking trails and a small pond.",
            category = "Parks"
        ),
        Location(
            name = "Sunset Viewpoint",
            description = "A quiet hilltop park popular for picnics and scenic views.",
            category = "Parks"
        ),
        Location(
            name = "Riverbend Dog Park",
            description = "Fenced area with separate sections for small and large dogs.",
            category = "Parks"
        ),
        Location(
            name = "Willow Creek Sports Field",
            description = "A park centered around multi-use athletic fields and bleachers.",
            category = "Parks"
        ),
        Location(
            name = "Botanical Gardens",
            description = "Beautifully maintained gardens with specialized plant collections.",
            category = "Parks"
        ),
        Location(
            name = "Westwood Mall",
            description = "Three-story shopping center featuring major department stores and a cinema.",
            category = "Shopping centers"
        ),
        Location(
            name = "The Open-Air Market",
            description = "Large complex of outdoor shops and specialty food vendors.",
            category = "Shopping centers"
        ),
        Location(
            name = "Midtown Galleria",
            description = "Upscale center known for luxury boutiques and high-end dining.",
            category = "Shopping centers"
        ),
        Location(
            name = "The Outlet Shoppes",
            description = "A collection of brand-name clearance and factory stores.",
            category = "Shopping centers"
        ),
        Location(
            name = "Community Plaza",
            description = "A neighborhood center with a grocery store, pharmacy, and small shops.",
            category = "Shopping centers"
        )
    )

    fun getDefaultCategory(): String {
        return "Coffee shops"
    }

    fun getDefaultLocation(): Location {
        return locations[0]
    }

    fun getListCategory(): List<String> {
        val listCategory = mutableListOf<String>()
        for (location in locations) {
            if (!listCategory.contains(location.category)) {
                listCategory.add(location.category)
            }
        }

        return listCategory
    }

    fun getListLocationByCategory(category: String): List<Location> {
        return locations.filter { location -> location.category == category }
    }
}