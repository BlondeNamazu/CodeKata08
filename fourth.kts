import java.io.File

val wordList = File("dictionary.txt").readLines()

val lengthToWordMap = wordList.groupBy { word -> word.length }

fun divideWord(word: String) : List<Pair<String,String>> {
  return (1..word.length)
    .mapNotNull { prefixLength ->
      lengthToWordMap[prefixLength]?.firstOrNull {
        word.startsWith(it)
      }
    }.mapNotNull { prefix ->
      val suffix = word.substring(prefix.length)
      if(lengthToWordMap[suffix.length]?.contains(suffix) == true) Pair(prefix, suffix)
      else null
    }
}

(lengthToWordMap[6] ?: emptyList<String>()).forEach { word ->
  val answers = divideWord(word)
  answers
    .forEach { pair ->
      println("${pair.first}${pair.second} = ${pair.first} + ${pair.second}")
    }
}

