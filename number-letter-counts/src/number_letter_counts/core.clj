(ns number-letter-counts.core
  (:gen-class))

(require '[clojure.string :as str])

(def digit-to-word
  {"1" "One"  "2" "Two" "3" "Three" "4" "Four"
   "5" "Five" "6" "Six" "7" "Seven" "8" "Eight"
   "9" "Nine" "0" ""    nil ""})

(def tens-to-word
  {"2" "Twenty" "3" "Thirty" "4" "Forty"
   "5" "Fifty"  "6" "Sixty"  "7" "Seventy"
   "8" "Eighty" "9" "Ninety" "0" ""})

(def teen-to-word
  {"0" "Ten"      "1" "Eleven"    "2" "Twelve"
   "3" "Thirteen" "4" "Fourteen"  "5" "Fifteen"  
   "6" "Sixteen"  "7" "Seventeen" "8" "Eighteen"
   "9" "Nineteen"})

(defn divisible? [num1, num2]
  (= (rem num1 num2) 0))

(defn get-array-of-stringified-digits [number]
  (str/split (.toString number) #"\D*"))

(defn get-hundredths-wording [digit number]
  (str (digit-to-word digit)
       (cond
         (= digit "0") ""
         (divisible? number 100) "Hundred"
         :else "HundredAnd")))

(defn get-tenths-wording [digit next-digit]
  (if (= digit "1")
    (teen-to-word next-digit)
    (str (tens-to-word digit) (digit-to-word next-digit))))

(defn digit-wording [digit digit-position number]
  (let [array (get-array-of-stringified-digits number)
        number-length (dec (count array))
        next-digit (get array (+ digit-position 1))
        position-from-left (- number-length digit-position)]
    (cond
      (= number-length 0) (digit-to-word digit)
      (= position-from-left 3) (str (digit-to-word digit) "Thousand")
      (= position-from-left 2) (get-hundredths-wording digit number)
      (= position-from-left 1) (get-tenths-wording digit next-digit))))

(defn convert-to-words [number]
  (let [array (get-array-of-stringified-digits number)
        length (dec (count array))]
    (loop [current-digit-position 0 final-string ""]
      (if (> current-digit-position length)
        final-string
        (recur (inc current-digit-position)
               (str final-string
                    (digit-wording (nth array current-digit-position)
                                   current-digit-position
                                   number)))))))

(defn get-sum-of-word-lengths [words]
  (reduce + (map count words)))

(defn -main
  [& args]
  (println (get-sum-of-word-lengths (map convert-to-words (range 1 1001)))))
