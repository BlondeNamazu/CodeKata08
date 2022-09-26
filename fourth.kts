import java.io.File

val wordList = File("dictionary.txt").readLines()

// O(N)
val lengthToWordMap = wordList.groupBy { word -> word.length }.mapValues { it.value.toSet() }

fun isWordContained(word: String): Boolean {
  return lengthToWordMap[word.length]?.contains(word) == true
}

// O(LNlog(N))
fun divideWord(word: String) : List<Pair<String,String>> {
  return (1..word.length)
    .mapNotNull { prefixLength ->
      val prefix = word.substring(0, prefixLength)
      val suffix = word.substring(prefixLength)
      if(isWordContained(prefix) && isWordContained(suffix)) Pair(prefix, suffix)
      else null
    }
}

// O(N) * divideWord = O(LN^2log(N))
(lengthToWordMap[6] ?: emptyList<String>()).forEach { word ->
  val answers = divideWord(word)
  answers
    .forEach { pair ->
      println("${pair.first}${pair.second} = ${pair.first} + ${pair.second}")
    }
}

// 計算量
// L: word length
// N: number of words in dictionary

