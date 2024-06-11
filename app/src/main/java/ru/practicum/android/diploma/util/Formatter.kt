package ru.practicum.android.diploma.util

object Formatter {
    /** Функция для склонения существительных с числительным.
    В конструктор передаются:
    quantity - количество (Int);
    singular - форма существительного в единственном числе (напр. "вакансия");
    singularGenitive - единственное число, родительный падеж (напр. "вакансии");
    pluralGenitive - множественное число, родительный падеж (напр. "вакансий").
    Все три формы берутся из ресурсов strings и передаются в конструктор в формате строки String.
    В качестве результата возвращается готовая форма с числительным (напр. "235 вакансий"). **/
    fun quantityWordFormFormatter(
        quantity: Int,
        singular: String,
        singularGenintive: String,
        pluralGenitive: String
    ): String {
        val result: String = quantity.toString()
        val resultLastTwo = result.takeLast(2).toInt()
        val resultLastOne = result.takeLast(1).toInt()
        var wordForm: String

        when (resultLastTwo) {
            in 11..19 -> {
                wordForm = pluralGenitive
            }

            else -> {
                if (resultLastOne == 0 || resultLastOne in 5..9) {
                    wordForm = pluralGenitive
                } else if (resultLastOne in 2..4) {
                    wordForm = singularGenintive
                } else {
                    wordForm = singular
                }
            }
        }

        return "$result $wordForm"
    }

}
