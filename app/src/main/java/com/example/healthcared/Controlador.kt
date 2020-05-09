package com.example.healthcared

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.healthcared.Modelo.*
import com.example.healthcared.UI.LogIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firestore.v1.FirestoreGrpc
import java.util.*

object Controlador: AppCompatActivity(){


    /* Ejercicios */
    val listasEjericios = initEjercicios()
    val ganarFuerza = listasEjericios[0]
    val bajarPeso = listasEjericios[1]
    val hipertrofia = listasEjericios[2]
    val cardio = listasEjericios[3]

    /* Dietas */
    val normal: Dieta = initDieta1()
    val veget: Dieta = initDieta2()
    val vegan: Dieta = initDieta3()


    //Contnedores personales, cargar con log-in

    var usuario: Usuario = Usuario("Random", "Random", "Random", false, false)  //En caso de ser necesario mantener qué usuario es el que está conectado
    var pasos: MutableCollection<Int>? =null

    //Contenedores de la app, cargar con inicio de la app
    var ejercicios: MutableCollection<Ejercicio>? =null
    var comidas: MutableCollection<Comida>? =null


    fun cargarDatos() {
        //Implementación de una carga de datos a las listas cuando se haga login
        //Puede ser init de la clase
        Log.d("Algo", mapOf("Hola" to 0, "quetal" to "mubien").toString())

        var auth: FirebaseAuth = FirebaseAuth.getInstance()
        var fStore: FirebaseFirestore = FirebaseFirestore.getInstance()

        var userId: String = auth.currentUser?.uid!!

        var documentReference: DocumentReference? = fStore.collection("Users").document(userId)
        Log.d("User ID", userId)

        if (documentReference != null) {
            documentReference.get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        var usuario_Map : Map<String, Object> = document.data?.get("userObject") as Map<String, Object>

                        //var tmp_usuario: Usuario? = usuario_Map.get("userObject")


                        usuario.fullname = usuario_Map["fullname"] as String
                        usuario.email = usuario_Map["email"] as String
                        usuario.gender = usuario_Map["gender"] as String
                        usuario.password = usuario_Map["password"] as String

                        usuario.targetSteps = usuario_Map["targetSteps"] as Long
                        usuario.height = usuario_Map["height"] as Long
                        usuario.weight = usuario_Map["weight"] as Long
                        usuario.lastDay = usuario_Map["lastDay"] as Long
                        usuario.cont = usuario_Map["cont"] as Long
                        usuario.numSteps = usuario_Map["numSteps"] as Long
                        usuario.rutinas = usuario_Map["rutinas"] as  MutableList<Rutina>
                        usuario.dietas = usuario_Map["dietas"] as  MutableList<Dieta>

                       Log.d("Username", usuario_Map.toString())

                        //this.usuario = tmp_user


                    } else {
                        Log.d("Error", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d("Error", "get failed with ", exception)
                }

        }
    }

    fun guardarDatos(){
        //Guardar datos en firebase para el usuario.
         var auth: FirebaseAuth
         var db : FirebaseFirestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val user = auth.currentUser
        var userID = user?.uid
        var documentReference = db.collection("Users").document(userID!!)
        var userData = mapOf("userObject" to this.usuario)
        documentReference.set(userData)
    }


    override fun onPause(){
        Log.d("On Pause", "Datos guardados")
        guardarDatos()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        guardarDatos()
    }


    fun initEjercicios(): MutableList<MutableList<Ejercicio>> {
        val pushup = Ejercicio("Push-Ups", "https://www.youtube.com/watch?v=yXuYJtRkmlM", 1)
        val squats = Ejercicio("Squats", "https://www.youtube.com/watch?v=C_VtOYc6j5c", 1)
        val squatsandjump = Ejercicio("Jump Squats", "https://www.youtube.com/watch?v=Azl5tkCzDcc", 2)
        val benchdip = Ejercicio("Bench Dips", "https://www.youtube.com/watch?v=c3ZGl4pAwZ4", 1)
        val diamonpushup = Ejercicio("Diamond Push-Ups", "https://www.youtube.com/watch?v=J0DnG1_S92I", 2)
        val plank = Ejercicio("Plank", "https://www.youtube.com/watch?v=pSHjTRCQxIw", 2)
        val legraise = Ejercicio("Leg Raises", "https://www.youtube.com/watch?v=l4kQd9eWclE", 2)
        val crunch = Ejercicio("Normal Crunch", "https://www.youtube.com/watch?v=NGRKFMKhF8s", 1)
        val jumpjacks = Ejercicio("Jumping Jacks", "https://www.youtube.com/watch?v=c4DAnQ6DtF8", 1)
        val hipraise = Ejercicio("Hip Raises", "https://www.youtube.com/watch?v=fDP6O_aJpDg", 1)
        val mountclimbers = Ejercicio("Mountain Climbers", "https://www.youtube.com/watch?v=De3Gl-nC7IQ", 2)
        val burpee = Ejercicio("Burpees", "https://www.youtube.com/watch?v=NCqbpkoiyXE", 2)

        var ganarFuerza: MutableList<Ejercicio> = mutableListOf(pushup, squats, squatsandjump, benchdip, diamonpushup, plank, legraise, crunch)
        var bajarPeso: MutableList<Ejercicio> = mutableListOf(jumpjacks, squats, pushup, hipraise, crunch, plank, burpee)
        var hipertrofia: MutableList<Ejercicio> = mutableListOf(pushup, squats, plank, legraise, crunch, benchdip, diamonpushup)
        var cardio: MutableList<Ejercicio> = mutableListOf(jumpjacks, benchdip, jumpjacks, squats, burpee, mountclimbers, crunch)

        return mutableListOf(ganarFuerza, bajarPeso, hipertrofia, cardio)
    }

    fun initDieta1(): Dieta {
        val avocadoEggToast = Comida("Avocado Egg Toast", 1, "http://fallback-origin.eatingwell.com/recipe/251334/avocado-toast-with-egg-spinach-salsa/", 1)
        val blackBeanNachoSoup = Comida("Black Bean Nacho Soup", 2, "http://fallback-origin.eatingwell.com/recipe/269841/loaded-black-bean-nacho-soup/", 1)
        val searedSalmon1 = Comida("Seared Salmon", 3, "http://fallback-origin.eatingwell.com/recipe/250543/seared-salmon-with-green-peppercorn-sauce/", 1)
        val greenBeans = Comida("Cup of Green Beans", 3, "-", 1)
        val greekYogurt = Comida("Plain Greek Yogurt", 3, "-", 1)
        val branCereal = Comida("Bowl of Bran Cereal", 1, "-", 2)
        val milk = Comida("Cup of Skim Milk", 1, "-", 2)
        val blueBerries = Comida("1/4 Cup of Blueberries", 1, "-", 2)
        val strawBerrySalad = Comida("Strawberry Salad", 2, "http://fallback-origin.eatingwell.com/recipe/252637/green-salad-with-strawberries-goat-cheese/", 2)
        val shrimpBuddhaBowl = Comida("Charred Shrimp Buddha Bowl", 3, "http://fallback-origin.eatingwell.com/video/9525/how-to-make-charred-shrimp-pesto-buddah-bowl/", 2)
        val greekYogurtB = Comida("Plain Greek Yogurt", 1, "-", 3)
        val blueBerries2 = Comida("3/4 Cup of Blueberries", 1, "-", 3)
        val strawBerrySalad2 = Comida("Strawberry Salad", 2, "http://fallback-origin.eatingwell.com/recipe/252637/green-salad-with-strawberries-goat-cheese/", 3)
        val curriedPotato = Comida("Curried Sweet Potato", 3, "http://fallback-origin.eatingwell.com/recipe/251575/curried-sweet-potatoes/", 3)
        val branCereal2 = Comida("1/3 Bowl of Bran Cereal", 1, "-", 4)
        val milk2 = Comida("Cup of Skim Milk", 1, "-", 4)
        val blueBerries3 = Comida("1/2 Cup of Blueberries", 1, "-", 4)
        val strawBerrySalad3 = Comida("Strawberry Salad", 2, "http://fallback-origin.eatingwell.com/recipe/252637/green-salad-with-strawberries-goat-cheese/", 4)
        val codTomatoSoup = Comida("Manhattan Cod Chowder", 3, "http://fallback-origin.eatingwell.com/recipe/252874/manhattan-cod-chowder/", 4)
        val brownRice = Comida("3/4 Cup of Cooker Brown Rice", 3, "-", 4)
        val rolledOats = Comida("1/2 Cup of Rolled Oats", 1, "-", 5)
        val milk3 = Comida("Cup of Skim Milk", 1, "-", 5)
        val raspberries = Comida("1 Cup of Raspberries", 1, "-", 5)
        val strawBerrySalad4 = Comida("Strawberry Salad", 2, "http://fallback-origin.eatingwell.com/recipe/252637/green-salad-with-strawberries-goat-cheese/", 5)
        val cauliflowerRice = Comida("Cauliflower Rice", 3, "http://fallback-origin.eatingwell.com/recipe/256497/cauliflower-rice-pilaf/", 5)
        val branCereal3 = Comida("Bowl of Bran Cereal", 1, "-", 6)
        val milk4 = Comida("Cup of Skim Milk", 1, "-", 6)
        val blueBerries4 = Comida("1 Cup of Blueberries", 1, "-", 6)
        val tunaSalad = Comida("Tuna and Dill Salad", 2, "http://fallback-origin.eatingwell.com/recipe/251516/walnut-dill-tuna-salad/", 6)
        val ovenTostadas = Comida("Butternut Squash and Black Bean Tostadas", 3, "http://fallback-origin.eatingwell.com/recipe/251241/butternut-squash-black-bean-tostadas/", 6)
        val avocadoEggToast2 = Comida("Avocado Egg Toast", 1, "http://fallback-origin.eatingwell.com/recipe/251334/avocado-toast-with-egg-spinach-salsa/", 7)
        val blueBerries5 = Comida("1 Cup of Blueberries", 1, "-", 7)
        val tunaSalad2 = Comida("Tuna and Dill Salad",2, "http://fallback-origin.eatingwell.com/recipe/251516/walnut-dill-tuna-salad/", 7)
        val skilletLemonChiken = Comida("Skillet Lemon Chiken", 3, "http://fallback-origin.eatingwell.com/recipe/272467/skillet-lemon-chicken-potatoes-with-kale/", 7)

        val dieta: MutableList<Comida> = mutableListOf(avocadoEggToast, blackBeanNachoSoup, searedSalmon1, greenBeans, greekYogurt, branCereal, milk, blueBerries, strawBerrySalad,
            shrimpBuddhaBowl, greekYogurtB, blueBerries2, strawBerrySalad2, curriedPotato, branCereal2, milk2, blueBerries3, strawBerrySalad3, codTomatoSoup, brownRice, rolledOats,
            milk3, raspberries, strawBerrySalad4, cauliflowerRice, branCereal3, milk4, blueBerries4, tunaSalad, ovenTostadas, avocadoEggToast2, blueBerries5, tunaSalad2, skilletLemonChiken)
        return Dieta("Normal", dieta)
    }

    fun initDieta2(): Dieta {
        val rolledOats = Comida("1/2 of Rolled Oats", 1, "-", 1)
        val milk = Comida("1/2 Cup of Skim Milk", 1, "-", 1)
        val raspberries = Comida("1/3 Cup of Raspberries", 1, "-", 1)
        val veggieWrap = Comida("Veggie Wrap", 2, "http://fallback-origin.eatingwell.com/recipe/249519/peanut-tofu-wrap/", 1)
        val quinoaBurger = Comida("Quinoa Veggie Burger", 3, "http://fallback-origin.eatingwell.com/recipe/250670/quinoa-veggie-burger/", 1)
        val bananaWalnut = Comida("Banana-Walnut Oatmeal", 1, "http://fallback-origin.eatingwell.com/recipe/248185/banana-walnut-oatmeal/", 2)
        val roastedVegetable = Comida("Roasted Vegetable Antipasto", 2, "http://fallback-origin.eatingwell.com/recipe/250583/roasted-vegetable-antipasto/", 2)
        val butternut = Comida("Butternut Squash & Black Bean Tostadas", 3, "http://fallback-origin.eatingwell.com/recipe/251241/butternut-squash-black-bean-tostadas/", 2)
        val bananaWalnut2 = Comida("Banana-Walnut Oatmeal", 1, "http://fallback-origin.eatingwell.com/recipe/248185/banana-walnut-oatmeal/", 3)
        val apple = Comida("1 Apple", 1, "-", 3)
        val roastedVegetable2 = Comida("Roasted Vegetable Antipasto", 2, "http://fallback-origin.eatingwell.com/recipe/250583/roasted-vegetable-antipasto/", 3)
        val pastaSalad = Comida("Garden Pasta Salad", 3, "http://fallback-origin.eatingwell.com/recipe/248955/garden-pasta-salad/", 3)
        val bananaWalnut3 = Comida("Banana-Walnut Oatmeal", 1, "http://fallback-origin.eatingwell.com/recipe/248185/banana-walnut-oatmeal/", 4)
        val apple2 = Comida("1 Apple", 1, "-", 4)
        val roastedVegetable4 = Comida("Roasted Vegetable Antipasto", 2, "http://fallback-origin.eatingwell.com/recipe/250583/roasted-vegetable-antipasto/", 4)
        val eggStuffed = Comida("Egg-Stuffed Potatoes", 3, "http://fallback-origin.eatingwell.com/recipe/259077/egg-stuffed-breakfast-potatoes/", 4)
        val avocadoEggToast = Comida("Avocado Egg Toast", 1, "http://fallback-origin.eatingwell.com/recipe/251334/avocado-toast-with-egg-spinach-salsa/", 5)
        val clementine = Comida("1 Clementine", 1, "-", 5)
        val roastedVegetable5 = Comida("Roasted Vegetable Antipasto", 2, "http://fallback-origin.eatingwell.com/recipe/250583/roasted-vegetable-antipasto/", 5)
        val tikkaMasala = Comida("Vegetarian Tikka Masala", 3, "http://fallback-origin.eatingwell.com/recipe/252514/vegetarian-tikka-masala/", 5)
        val brownRice = Comida("3/4 Cup of Cooker Brown Rice", 3, "-", 5)
        val rolledOats2 = Comida("1/2 of Rolled Oats", 1, "-", 6)
        val milk2 = Comida("1/2 Cup of Skim Milk", 1, "-", 6)
        val raspberries2 = Comida("1/3 Cup of Raspberries", 1, "-", 6)
        val veggieWrap2 = Comida("Veggie Wrap", 2, "http://fallback-origin.eatingwell.com/recipe/249519/peanut-tofu-wrap/", 6)
        val veggieTaco = Comida("Veggie Tacos", 3, "http://fallback-origin.eatingwell.com/recipe/257781/chipotle-lime-cauliflower-tacos/", 6)
        val rolledOats3 = Comida("1/2 of Rolled Oats", 1, "-", 7)
        val milk3 = Comida("1/2 Cup of Skim Milk", 1, "-", 7)
        val apple3 = Comida("1 Apple", 1, "-", 7)
        val veggieWrap3 = Comida("Veggie Wrap", 2, "http://fallback-origin.eatingwell.com/recipe/249519/peanut-tofu-wrap/", 7)
        val chickpea = Comida("Chickpea Stew", 3, "http://fallback-origin.eatingwell.com/recipe/248777/fragrant-chickpea-stew/", 7)

        val dieta: MutableList<Comida> = mutableListOf(rolledOats, milk, raspberries, veggieWrap, quinoaBurger, bananaWalnut, roastedVegetable, butternut, bananaWalnut2, apple, roastedVegetable2,
            pastaSalad, bananaWalnut3, apple2, roastedVegetable4, eggStuffed, avocadoEggToast, clementine, roastedVegetable5, tikkaMasala, brownRice, rolledOats2, milk2, raspberries2, veggieWrap2,
            veggieTaco, rolledOats3, milk3, apple3, veggieWrap3, chickpea)
        return Dieta("Vegetariana", dieta)
    }

    fun initDieta3(): Dieta {
        val pancakes = Comida("Vegan Pancakes", 1, "http://fallback-origin.eatingwell.com/recipe/257348/vegan-pancakes/", 1)
        val blueberries = Comida("1/4 Cup of Blueberries", 1, "-", 1)
        val avocadoToast = Comida("West Coast Avocado Toast", 2, "http://fallback-origin.eatingwell.com/recipe/257117/west-coast-avocado-toast/", 1)
        val faladelSalad = Comida("Falafel Salad", 3, "http://fallback-origin.eatingwell.com/recipe/253015/falafel-salad-with-lemon-tahini-dressing/", 1)
        val englishMuffin = Comida("Chia Berry English Muffin", 1, "http://fallback-origin.eatingwell.com/recipe/255160/peanut-butter-chia-berry-jam-english-muffin/", 2)
        val salad = Comida("White Bean Salad", 2, "http://fallback-origin.eatingwell.com/recipe/259819/white-bean-veggie-salad/", 2)
        val buddha = Comida("Back Bean Buddha Bowl", 3, "http://fallback-origin.eatingwell.com/recipe/260726/black-bean-quinoa-buddha-bowl/", 2)
        val englishMuffin2 = Comida("Cinnamon English Muffin", 1, "http://fallback-origin.eatingwell.com/recipe/249320/peanut-butter-cinnamon-english-muffin/", 3)
        val edamameSalad = Comida("Edamame Salad", 2, "http://fallback-origin.eatingwell.com/recipe/252724/super-green-edamame-salad/", 3)
        val currySoup = Comida("Cauliflower & Potato Curry Soup", 3, "http://fallback-origin.eatingwell.com/recipe/256519/roasted-cauliflower-potato-curry-soup/", 3)
        val quinoaMix = Comida("Chia & Quinoa Mix", 1, "http://fallback-origin.eatingwell.com/recipe/255762/quinoa-chia-oatmeal-mix/", 4)
        val currySoup2 = Comida("Cauliflower & Potato Curry Soup", 2, "http://fallback-origin.eatingwell.com/recipe/256519/roasted-cauliflower-potato-curry-soup/", 4)
        val sweetPotatoes = Comida("Sweet Potato Bisque", 3, "http://fallback-origin.eatingwell.com/recipe/252458/sweet-potato-peanut-bisque/", 4)
        val pancakes2 = Comida("Vegan Pancakes", 1, "http://fallback-origin.eatingwell.com/recipe/257348/vegan-pancakes/", 5)
        val blueberries2 = Comida("1/4 Cup of Blueberries", 1, "-", 5)
        val sandwich = Comida("Veggie & Hummus Sandwich", 2, "http://fallback-origin.eatingwell.com/recipe/259817/veggie-hummus-sandwich/", 5)
        val chickpea = Comida("Chickpea Curry", 3, "http://fallback-origin.eatingwell.com/recipe/255186/chickpea-curry-chhole/", 5)
        val englishMuffin3 = Comida("Chia Berry English Muffin", 1, "http://fallback-origin.eatingwell.com/recipe/255160/peanut-butter-chia-berry-jam-english-muffin/", 6)
        val cauliflowerSalad = Comida("Cauliflower Egg Salad", 2, "http://fallback-origin.eatingwell.com/recipe/252738/vegan-cauliflower-egg-salad/", 6)
        val thaiSpaghetti = Comida("Thai Spaghetti Squash", 3, "http://fallback-origin.eatingwell.com/recipe/266648/thai-spaghetti-squash-with-peanut-sauce/", 6)
        val pancakes3 = Comida("Vegan Pancakes", 1, "http://fallback-origin.eatingwell.com/recipe/257348/vegan-pancakes/", 7)
        val blackberries = Comida("1/4 Cup of Blackberries", 1, "-", 7)
        val edamameSalad2 = Comida("Edamame Salad", 2, "http://fallback-origin.eatingwell.com/recipe/252724/super-green-edamame-salad/", 7)
        val rainbow = Comida("Rainbow Veggie Bowl", 3, "http://fallback-origin.eatingwell.com/recipe/255570/rainbow-veggie-spring-roll-bowl/", 7)

        val dieta: MutableList<Comida> = mutableListOf(pancakes, blueberries, avocadoToast, faladelSalad, englishMuffin, salad, buddha, englishMuffin2, edamameSalad, currySoup, quinoaMix, currySoup2,
            sweetPotatoes, pancakes2, blueberries2, sandwich, chickpea, englishMuffin3, cauliflowerSalad, thaiSpaghetti, pancakes3, blackberries, edamameSalad2, rainbow)
        return Dieta("Vegana", dieta)
    }

}