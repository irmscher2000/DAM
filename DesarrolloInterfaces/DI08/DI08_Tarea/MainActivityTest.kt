package com.example.navegacionentreventanas

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.espresso.action.ViewActions.replaceText
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Clase para realizar pruebas utilizando Espresso
 * 
 * @author Eugen Moga
 */
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    // PRUEBA 1: Carga de la pantalla principal
    // Comprueba que la MainActivity se abre correctamente
    @Test
    fun test_MainActivity_Carga(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.lytContenedor)).check(matches(isDisplayed()))
    }

    // PRUEBA 2: Verifica que el EditText y el boton Aceptar se ven correctamente
    @Test
    fun test_Visibilidad_EditText_y_Boton(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.txtNombre)).check(matches(isDisplayed()))
        onView(withId(R.id.btnAceptar)).check(matches(isDisplayed()))
    }

    // PRUEBA 3: Se comprueba la navegación entre pantallas
    // Simulando la pulsación del boton y comprobando que se abre la segunda actividad.
    @Test
    fun test_Navegacion_a_SaludoActivity(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.txtNombre)).perform(replaceText("Test"), closeSoftKeyboard())
        onView(withId(R.id.btnAceptar)).perform(click())
        onView(withId(R.id.lyyContenedorSaludo)).check(matches(isDisplayed()))
    }

    // PRUEBA 4: Introduce el nombre y se comprueba que aparece en la segunda pantalla
    @Test fun test_Paso_Nombre_Mostrado_En_SaludoActivity(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.txtNombre)).perform(replaceText("Eugen"),closeSoftKeyboard())
        onView(withId(R.id.btnAceptar)).perform(click())
        onView(withId(R.id.lyyContenedorSaludo)).check(matches(isDisplayed()))
        onView(withId(R.id.txtSaludo)).check(matches(withText("Hola Eugen")))
    }

    // PRUEBA 5: Flujo completo, se comprueba la navegacion entre las pantallas.
    @Test
    fun test_Flujo_Completo_Navegar_y_Volver(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.txtNombre)).perform(replaceText("Eugen"), closeSoftKeyboard())
        onView(withId(R.id.btnAceptar)).perform(click())
        onView(withId(R.id.lyyContenedorSaludo)).check(matches(isDisplayed()))
        onView(withId(R.id.volver)).perform(click())
        onView(withId(R.id.lytContenedor)).check(matches(isDisplayed()))
    }
}