package ru.practicum.android.diploma.util

object Formatter {

    const val ZERO = 0
    const val TWO = 2
    const val FOUR = 4
    const val FIVE = 5
    const val NINE = 9
    const val ELEVEN = 11
    const val NINETEEN = 19

    /**
     * Функция для склонения существительных с числительным.
     * В конструктор передаются:
     * quantity - количество (Int);
     * singular - форма существительного в единственном числе (напр. "вакансия");
     * singularGenitive - единственное число, родительный падеж (напр. "вакансии");
     * pluralGenitive - множественное число, родительный падеж (напр. "вакансий").
     * Все три формы берутся из ресурсов strings и передаются в конструктор в формате строки String.
     * В качестве результата возвращается готовая форма с числительным (напр. "235 вакансий").
     */
    fun quantityWordFormFormatter(
        quantity: Int,
        singular: String,
        singularGenitive: String,
        pluralGenitive: String
    ): String {
        val result: String = quantity.toString()
        val resultLastTwo = result.takeLast(2).toInt()
        val resultLastOne = result.takeLast(1).toInt()
        var wordForm: String

        when (resultLastTwo) {
            in ELEVEN..NINETEEN -> {
                wordForm = pluralGenitive
            }

            else -> {
                if (resultLastOne == ZERO || resultLastOne in FIVE..NINE) {
                    wordForm = pluralGenitive
                } else if (resultLastOne in TWO..FOUR) {
                    wordForm = singularGenitive
                } else {
                    wordForm = singular
                }
            }
        }

        return "$wordForm"
    }

}
