import java.io.File

val wordList = File("../dictionary.txt").readLines()

val originalWordLength = 6

val lengthToWordMap = wordList.groupBy { word -> word.length }.mapValues { it.value.toSet() }

fun isWordContained(word: String): Boolean {
  return lengthToWordMap[word.length]?.contains(word) == true
}

// "hoge", 3 -> ("ho", ["g","e"])
fun dividePrefix(word: String, divideCount: Int) : List<Pair<String,List<String>>> {
  return if (divideCount == 1) {
    return if (isWordContained(word)) listOf(Pair(word, emptyList()))
    else emptyList()
  } else {
    (1..word.length)
      .mapNotNull { prefixLength ->
        val prefix = word.substring(0, prefixLength)
        if (isWordContained(prefix)) {
          val suffix = word.substring(prefixLength)
          val ret = dividePrefix(suffix, divideCount - 1)
          ret.map { (childPrefix, suffixList) ->
            Pair(prefix, listOf(childPrefix).plus(suffixList))
          }
        } else null
      }
      .flatten()
  }
}

(lengthToWordMap[originalWordLength] ?: emptyList<String>()).forEach { word ->
  (2 until originalWordLength).forEach { length ->
    val answers = dividePrefix(word, length)
    answers
      .forEach { pair ->
        val list = listOf(pair.first).plus(pair.second)
        list.forEach{ print("$it,") }
        println()
      }
  }
}

// 計算量
// L: word length
// N: number of words in dictionary

