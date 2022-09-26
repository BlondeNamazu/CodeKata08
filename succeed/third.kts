import java.io.File

val wordList = File("../dictionary.txt").readLines()

val originalWordLength = 6
va

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

// O(LNlog(N))
// "hoge", 3 -> ("ho", ["g","e"])
fun dividePrefix(word: String, divideCount: Int) : List<Pair<String,List<String>>> {
  if (divideCount == 1) {
    return if (isWordContained(word)) listOf(Pair(word, emptyList()))
    else emptyList()
  } else {
    (1..word.length)
      .mapNotNull { prefixLength
        val prefix = word.substring(0, prefixLength)
        return if (isWordContained(prefix)) {
          val suffix = word.substring(prefixLength)
          val ret = dividePrefix(suffix, divideCount - 1)
          ret.map { (childPrefix, suffixList) ->
            Pair(prefix, listOf(childPrefix).plus(suffixList))
          }
        } else null
      }
  }

// O(N) * divideWord = O(LN^2log(N))
(lengthToWordMap[originalWordLength] ?: emptyList<String>()).forEach { word ->
  val answers = divideWord(word)
  answers
    .forEach { pair ->
      println("${pair.first}${pair.second} = ${pair.first} + ${pair.second}")
    }
}

// 計算量
// L: word length
// N: number of words in dictionary

