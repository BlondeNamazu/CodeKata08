import java.io.File

val wordList = File("dictionary.txt").readLines()

val lengthToWordMap = wordList.groupBy { word -> word.length }

val composedWordLength = 6

(1 until composedWordLength).forEach { firstWordLength ->
  val secondWordLength = composedWordLength - firstWordLength
  
  val firstWordCandidates = lengthToWordMap[firstWordLength] ?: emptyList<String>()
  val secondWordCandidates = lengthToWordMap[secondWordLength] ?: emptyList<String>()
  val composedWordCandidates = lengthToWordMap[composedWordLength] ?: emptyList<String>()

  firstWordCandidates.forEach { firstWord ->
    secondWordCandidates.forEach { secondWord ->
      val composedWord = firstWord + secondWord
      if (composedWordCandidates.contains(composedWord)) {
        println("$firstWord + $secondWord => $composedWord")
      }
    }
  }
}

