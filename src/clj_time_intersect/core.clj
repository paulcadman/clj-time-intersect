(ns clj-time-intersect.core
  (:require [clj-time.core :as t]))

(defn intersect
  "Return the intersection of two time intervals
   Returns nil if the two time intervals do not intersect."
  [i1 i2]
  (let [i1_start  (t/start i1)
        i1_end     (t/end i1)
        i2_start (t/start i2)
        i2_end   (t/end i2)]
          (cond
            (or (t/before? i1_end i2_start)
                (t/before? i2_end i1_end))
            nil
            (and (t/after? i1_start i2_start)
                 (t/before? i1_end i2_end))
            i1
            (and (t/before? i1_start i2_start)
                 (t/before? i1_end i2_end))
            (t/interval i2_start i1_end)
            (and (t/after? i1_start i2_start)
                 (t/after? i1_end i2_end))
            (t/interval i1_start i2_end)
            (and (t/before? i1_start i2_start)
                 (t/after? i1_end i2_end))
            i2)))
