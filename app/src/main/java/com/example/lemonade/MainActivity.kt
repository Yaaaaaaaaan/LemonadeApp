/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lemonade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    /**
     * DO NOT ALTER ANY VARIABLE OR VALUE NAMES OR THEIR INITIAL VALUES.
     *
     * Anything labeled var instead of val is expected to be changed in the functions but DO NOT
     * alter their initial values declared here, this could cause the app to not function properly.
     */
    private val LEMONADE_STATE = "LEMONADE_STATE"
    private val LEMON_SIZE = "LEMON_SIZE"
    private val SQUEEZE_COUNT = "SQUEEZE_COUNT"
    // SELECT represents the "pick lemon" state
    private val SELECT = "select"
    // SQUEEZE represents the "squeeze lemon" state
    private val SQUEEZE = "squeeze"
    // DRINK represents the "drink lemonade" state
    private val DRINK = "drink"
    // RESTART represents the state where the lemonade has been drunk and the glass is empty
    private val RESTART = "restart"
    // Default the state to select
    private var lemonadeState = "select"
    // Default lemonSize to -1
    private var lemonSize = -1
    // Default the squeezeCount to -1
    private var squeezeCount = -1

    private var lemonTree = LemonTree()
    private var lemonImage: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // === DO NOT ALTER THE CODE IN THE FOLLOWING IF STATEMENT ===
        if (savedInstanceState != null) {
            lemonadeState = savedInstanceState.getString(LEMONADE_STATE, "select")
            lemonSize = savedInstanceState.getInt(LEMON_SIZE, -1)
            squeezeCount = savedInstanceState.getInt(SQUEEZE_COUNT, -1)
        }
        // === END IF STATEMENT ===

        lemonImage = findViewById(R.id.image_lemon_state)
        setViewElements()
        lemonImage!!.setOnClickListener {
            clickLemonImage()
        }
        lemonImage!!.setOnLongClickListener {

            // TODO: replace 'false' with a call to the function that shows the squeeze count
            // TODO: substitua 'false' por uma chamada para a função que mostra a contagem de compressão
            showSnackbar()
        }
    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     *
     * This method saves the state of the app if it is put in the background.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(LEMONADE_STATE, lemonadeState)
        outState.putInt(LEMON_SIZE, lemonSize)
        outState.putInt(SQUEEZE_COUNT, squeezeCount)
        super.onSaveInstanceState(outState)
    }

    /**
     * Clicking will elicit a different response depending on the state.
     * This method determines the state and proceeds with the correct action.
     */
    private fun clickLemonImage() {
        when (lemonadeState){
            SELECT -> {
                lemonadeState = SQUEEZE
                val tree: LemonTree = lemonTree
                lemonSize= tree.pick();
                squeezeCount =0
            }
            SQUEEZE ->{
                squeezeCount +=1
                lemonSize -=1
                lemonadeState = if (lemonSize ==0){
                    DRINK
                }else SQUEEZE
            }
            DRINK ->{
                lemonadeState = RESTART
                lemonSize =-1
            }
            RESTART -> lemonadeState = SELECT
        }
        setViewElements()

        // TODO: use a conditional statement like 'if' or 'when' to track the lemonadeState
        //  when the image is clicked we may need to change state to the next step in the
        //  lemonade making progression (or at least make some changes to the current state in the
        //  case of squeezing the lemon). That should be done in this conditional statement

        // TODO: When the image is clicked in the SELECT state, the state should become SQUEEZE
        //  - The lemonSize variable needs to be set using the 'pick()' method in the LemonTree class
        //  - The squeezeCount should be 0 since we haven't squeezed any lemons just yet.

        // TODO: When the image is clicked in the SQUEEZE state the squeezeCount needs to be
        //  INCREASED by 1 and lemonSize needs to be DECREASED by 1.
        //  - If the lemonSize has reached 0, it has been juiced and the state should become DRINK
        //  - Additionally, lemonSize is no longer relevant and should be set to -1

        // TODO: When the image is clicked in the DRINK state the state should become RESTART

        // TODO: When the image is clicked in the RESTART state the state should become SELECT

        // TODO: lastly, before the function terminates we need to set the view elements so that the
        //  UI can reflect the correct state

        // TODO: use uma instrução condicional como 'if' ou 'when' para rastrear o lemonadeState
        //  quando a imagem é clicada, podemos precisar mudar de estado para a próxima etapa no
        //  progressão fazendo limonada (ou pelo menos fazer algumas mudanças no estado atual no
        //  caso de espremer o limão). Isso deve ser feito nesta declaração condicional

        // TODO: Quando a imagem é clicada no estado SELECT, o estado deve se tornar SQUEEZE
        //  - A variável lemonSize precisa ser definida usando o método 'pick()' na classe LemonTree
        //  - O squeezeCount deve ser 0 já que ainda não esprememos nenhum limão.

        // TODO: Quando a imagem é clicada no estado SQUEEZE, o squeezeCount precisa ser
        //  AUMENTADO em 1 e lemonSize precisa ser DIMINUÍDO em 1.
        //  - Se o lemonSize atingiu 0, ele foi espremido e o estado deve se tornar DRINK
        //  - Além disso, lemonSize não é mais relevante e deve ser definido como -1

        // TODO: Quando a imagem é clicada no estado DRINK o estado deve se tornar RESTART

        // TODO: Quando a imagem é clicada no estado RESTART o estado deve se tornar SELECT

        // TODO: por último, antes que a função termine, precisamos definir os elementos da view para que o
        //  UI pode refletir o estado correto
    }

    /**
     * Set up the view elements according to the state.
     */
    private fun setViewElements() {
        val textAction: TextView = findViewById(R.id.text_action)
        val lemonImage:ImageView=findViewById(R.id.image_lemon_state)

        when(lemonadeState){
            SELECT -> {
                textAction.text= "Click to select a lemon!"
                lemonImage.setImageResource(R.drawable.lemon_tree)
            }
            SQUEEZE -> {
                textAction.text = "Click to juice the lemon"
                lemonImage.setImageResource(R.drawable.lemon_squeeze)
            }
            DRINK -> {
                textAction.text = "Click to drink your lemonade!"
                lemonImage.setImageResource(R.drawable.lemon_drink)
            }
            RESTART -> {
                textAction.text = "Click to start again!"
                lemonImage.setImageResource(R.drawable.lemon_restart)
            }
        }
        // TODO: set up a conditional that tracks the lemonadeState


        // TODO: for each state, the textAction TextView should be set to the corresponding string from
        //  the string resources file. The strings are named to match the state

        // TODO: para cada estado, o textAction TextView deve ser definido para a string correspondente de
        //  o arquivo de recursos de string. As strings são nomeadas para corresponder ao estado


        // TODO: Additionally, for each state, the lemonImage should be set to the corresponding
        //  drawable from the drawable resources. The drawables have the same names as the strings
        //  but remember that they are drawables, not strings.

        // TODO: Além disso, para cada estado, o lemonImage deve ser definido para o valor correspondente
        //  desenhável dos recursos desenháveis. Os drawables têm os mesmos nomes que as strings
        //  mas lembre-se que eles são drawables, não strings.
    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     *
     * Long clicking the lemon image will show how many times the lemon has been squeezed.
     */
    private fun showSnackbar(): Boolean {
        if (lemonadeState != SQUEEZE) {
            return false
        }
        val squeezeText = getString(R.string.squeeze_count, squeezeCount)
        Snackbar.make(
            findViewById(R.id.constraint_Layout),
            squeezeText,
            Snackbar.LENGTH_SHORT
        ).show()
        return true
    }
}

/**
 * A Lemon tree class with a method to "pick" a lemon. The "size" of the lemon is randomized
 * and determines how many times a lemon needs to be squeezed before you get lemonade.
 */
class LemonTree {
    fun pick(): Int {
        return (2..4).random()
    }
}
