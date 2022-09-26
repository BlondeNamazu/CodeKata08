import java.io.File

val wordList = File("dictionary.txt").readLines()

val lengthToWordMap = wordList.groupBy { word -> word.length }.mapValues { it.value.toSet() }

fun divideWord(word: String) : List<Pair<String,String>> {
  return (1..word.length)
    .filter { prefixLength ->
      val prefix = word.substring(0, prefixLength)
      lengthToWordMap[prefixLength]?.contains(prefix) == true
    }.mapNotNull { prefixLength ->
      val prefix = word.substring(0, prefixLength)
      val suffix = word.substring(prefixLength)
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

