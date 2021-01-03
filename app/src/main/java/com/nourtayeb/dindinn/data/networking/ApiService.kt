package com.nourtayeb.dindinn.data.networking

import com.nourtayeb.dindinn.domain.entity.CartItem
import com.nourtayeb.dindinn.domain.entity.Category
import com.nourtayeb.dindinn.domain.entity.Product
import com.nourtayeb.dindinn.domain.entity.Promotion
import io.reactivex.Single

class ApiService {
    fun getPromotions() = Single.fromCallable {
        Thread.sleep(1000)
        listOf(
            Promotion(
                1L,
                "https://www.couponlisty.com/wp-content/uploads/2017/03/Freshmenu-Coupon-848x477.jpg"
            ),
            Promotion(
                2L,
                "https://cdn.grabon.in/gograbon/images/category/1546252575451.png"
            ),
            Promotion(
                3L,
                "https://timesofindia.indiatimes.com/thumb/msid-65771570,imgsize-209655,width-400,resizemode-4/65771570.jpg"
            )
        )
    }

    fun getProducts() = Single.fromCallable {
        Thread.sleep(3000)
        listOf(
            Product(
                1,
                1,
                "Vegatables Pizza",
                "https://stanspizzajoint.com/wp-content/uploads/2019/10/Thin_Crust_Veggie_Pizza-1080x675.jpg",
                "Tomato, cheese, olives",
                10,
                "300 g",
                "20cm",
                "Pizzas"
            ),
            Product(
                2,
                1,
                "Pepperoni Pizza",
                "https://cdn.tasteatlas.com/images/dishes/b05a0af72ad845f3a6abe16143d7853a.jpg?w=600&h=450",
                "Mozarilla, Pepperoni",
                13,
                "350 g",
                "20cm",
                "Pizzas"
            ),
            Product(
                3,
                1,
                "Chicken Pizza",
                "https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/x17/2014_garlic-chicken-pizza_600x600.jpg",
                "Pepper, Chicken",
                16,
                "320 g",
                "25cm",
                "Pizzas"
            ),
            Product(
                4,
                2,
                "Chicken Shawarma Sandwich",
                "https://lifeloveandgoodfood.com/wp-content/uploads/2020/04/Chicken-Shawarma_09_1200x1200-720x540.jpg",
                "Chicken, Mayoneese",
                7,
                "100 g",
                "10cm",
                "Sandwich"
            ),
            Product(
                5,
                2,
                "Beef Shawarma Sandwich",
                "https://i.pinimg.com/originals/a5/f2/40/a5f240a88bb7f16e3393c631d1142fc8.png",
                "Beef, Mayoneese",
                9,
                "100 g",
                "10cm",
                "Sandwich"
            ),
            Product(
                6,
                2,
                "Mixed Shawarma Dish",
                "https://s3-media3.fl.yelpcdn.com/bphoto/mRPLtuFE5605lzx4mWOuog/o.jpg",
                "Beef, Chicken, Pickels",
                20,
                "200 g",
                "20cm",
                "Dish"
            ),
            Product(
                7,
                3,
                "Burger Small",
                "https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/x17/2019_df_retail_butter-burger_20912_760x580.jpg",
                "Burger, Tomtaoes, Onion, Lattuce",
                22,
                "100 g",
                "10cm",
                "Sandwich"
            ),

            Product(
                8,
                3,
                "Burger Mashroom",
                "https://www.sprinklesandsprouts.com/wp-content/uploads/2020/03/Mushroom-Swiss-Burger-SQ.jpg",
                "Burger, Tomtaoes, Onion, Mashroom",
                26,
                "150 g",
                "13cm",
                "Sandwich"
            ),

            )
    }

    val categories = listOf<Category>(
        Category(1, "Pizza"),
        Category(2, "Shawarma"),
        Category(3, "Burger"),
    )

    fun getCategories() = Single.fromCallable {
        Thread.sleep(1000)
        categories
    }

    val cart = mutableSetOf<CartItem>()
    fun getCart() = Single.fromCallable {
        cart
    }

    fun addToCart(productId: Long) = Single.fromCallable {
        val cartItem = cart.firstOrNull { it.productId == productId }
        if (cartItem == null) {
            val cartItem = CartItem(productId, 1)
            cart.add(cartItem)
            cartItem
        } else {
            cartItem
//            val newQuantity = cartItem.quantity + 1
//            cartItem.copy(quantity = newQuantity)
        }
    }
}