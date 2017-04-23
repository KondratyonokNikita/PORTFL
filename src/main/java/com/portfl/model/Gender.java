package com.portfl.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Samsung on 13.04.2017.
 */
//        2.  - андроген, гермафродит (мужчиноженщина)
//        3. Androgynous - мужеженственный (внутренне, по ощущениям)
//        4. Bigender - ощущающие себя в разное время то мужчиной, то женщиной
//        5 Cis - латинск. «пред-», т.е. «недо-» (без негативной коннотации)
//        6. Cis Female - предженский, недоженский
//        7. Cis Male - предмужской, недомужской
//        8. Cis Man - предмужчина, недомужчина
//        9. Cis Woman - предженщина, недоженщина
//        10. Cisgender - предполовой, недополовой
//        11. Cisgender Female - женский предпол, недополовой женский
//        12. Cisgender Male - мужской предпол, недополовой мужской
//        13. Cisgender Man - предполовой мужчина, недополовой мужчина
//        14. Cisgender Woman - предполовая женщина, недополовая женщина
//        15. Female to Male - от женского к мужскому
//        16. FTM - женщина, хирургически, внешне, принявшая облик мужчины
//        17. Gender Fluid - неустойчивый, «текучий»
//        18. Gender Nonconforming - отрицающий традиционную классификацию
//        19. Gender Questioning - пол, остающийся под вопросом
//        20. Gender Variant - пол, допускающий несколько вариантов
//        21. Genderqueer - свой особенный, своеобычный
//        22. Intersex - межполовой
//        23. Male to Female - от мужчины к женщине
//        24. MTF - мужчина, хирургически, внешне, принявший облик женщины
//        25. Neither - ни тот, ни другой (из двух традиционных)
//        26. Neutrois - стремящиеся устранить половые признаки во внешнем виде
//        27. Non-binary - отрицающий систему двух полов
//        28. Other - другое
//        29. Pangender - всеобщеполовой
//        30. Trans - переходной к другому полу
//        31. Trans Female - переходной к женскому половому состоянию
//        32. Trans Male - переходной к мужскому половому состоянию
//        33. Trans Man - переходной к мужчине
//        34. Trans Person - переходной к лицу, вне половой классификации
//        35. Trans Woman - переходной к женщине
//        36. Trans(asterisk) - переходной к другому полу (* - с сохранением тайны)
//        37. Trans(asterisk)Female - переходной к женскому половому состоянию (*)
//        38. Trans(asterisk)Male - переходной к мужскому половому состоянию(*)
//        39. Trans(asterisk)Man - переходной к мужчине(*)
//        40. Trans(asterisk)Person - переходной к лицу, вне половой классификации(*)
//        41. Trans(asterisk)Woman - переходной к женщине(*)
//        42. Transexual - транссексуальный
//        43. Transexual Female - женский траннсексуальный
//        44. Transexual Male - мужской транссексуальный
//        45. Transexual Man - мужчина транссексуал
//        46. Transexual Person - лицо траннсексуал
//        47. Transexual Woman - женщина транссексуал
//        48. Transgender Female
//        49. Transgender Male
//        50. Transgender Man
//        51. Transgender Person
//        52. Transgender Woman
//        53. Transmasculine - «за пределами мужского» (фантазии на тему мужского пола)
//        54. Two-spirit - две души, «двудушный» (без негативной коннотации)
public enum Gender {
    AGENDER("Agender"),
    ANDROGYNE("Androgyne"),
    NONGENDER("Non-binary"),
    OTHER("Other"),
    PANGENDER("Pangender"),
    TRANS("Trans"),
    TRANSEXUAL("Transexual"),
    TRANSGENDER("Transgender"),
    TRANSMASULINE("Transmasculine"),
    TWOSPIRIN("Two-spirit");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
