import java.io.File

val wordList = File("dictionary.txt").readLines()

val lengthToWordMap = wordList.groupBy { word -> word.length }

fun divideWord(word: String, prefixList: List<String>) : List<List<String>> {
  return (1..word.length)
    .mapNotNull { prefixLength ->
      lengthToWordMap[prefixLength]?.firstOrNull {
        word.startsWith(it)
      }
    }.flatMap { prefix ->
      if(prefix.length == word.length) listOf(prefixList.plus(prefix))
      else divideWord(word.substring(prefix.length), prefixList.plus(prefix))
    }
}

(lengthToWordMap[6] ?: emptyList<String>()).forEach { word ->
  val answers = divideWord(word, emptyList())
  answers
    .filter { it.size == 2}
    .forEach { list ->
      list.forEach { word ->
        print("$word,") 
      }
      println()
    }
}

