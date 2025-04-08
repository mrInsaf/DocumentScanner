package com.mrinsaf.core.data.repository.docDocTypeRepository.docType

import com.mrinsaf.core.data.model.DocType
import com.mrinsaf.core.data.repository.docWorkTypeRepository.docType.DocTypeDataSource

object LocalDocTypeDataSource : DocTypeDataSource {
    private val map = mapOf(
        "000" to DocType("000", "Общая часть или документация в целом (общие работы, выполняемые несколькими специальностями)"),
        "001" to DocType("001", "Генеральный план"),
        "002" to DocType("002", "Инженерные изыскания"),
        "003" to DocType("003", "Генеральный план и транспорт"),
        "004" to DocType("004", "Резерв"),
        "005" to DocType("005", "Резерв"),
        "006" to DocType("006", "Резерв"),
        "007" to DocType("007", "Резерв"),
        "008" to DocType("008", "Конструкции металлические деталировочные"),
        "009" to DocType("009", "Охрана памятников истории и культуры"),
        "010" to DocType("010", "Архитектурно-строительные решения"),
        "011" to DocType("011", "Архитектурные решения"),
        "012" to DocType("012", "Конструкции железобетонные"),
        "013" to DocType("013", "Конструкции металлические"),
        "014" to DocType("014", "Строительная часть гидротехнических сооружений"),
        "015" to DocType("015", "Организация строительства"),
        "016" to DocType("016", "Мероприятия по защите окружающей среды"),
        "017" to DocType("017", "Грунты"),
        "018" to DocType("018", "Динамика и сейсмостойкость"),
        "019" to DocType("019", "Гражданская оборона"),
        "020" to DocType("020", "Технология производства"),
        "021" to DocType("021", "Тепломеханические решения (тепломеханика)"),
        "022" to DocType("022", "Безопасность и надежность АЭС"),
        "023" to DocType("023", "Водоподготовка"),
        "024" to DocType("024", "Гидротехнические решения и техническое водоснабжение (гидротехника, техническое водоснабжение)"),
        "025" to DocType("025", "Переработка и хранение радиоактивных отходов (жидких)"),
        "026" to DocType("026", "Газоснабжение (внутренние устройства)"),
        "027" to DocType("027", "Газоснабжение (наружные газопроводы)"),
        "028" to DocType("028", "Тепловая изоляция оборудования и трубопроводов (тепловая изоляция)"),
        "029" to DocType("029", "Антикоррозийная защита оборудования (технологических аппаратов, газоходов и трубопроводов), Антикоррозийная защита конструкций зданий, сооружений"),
        "030" to DocType("030", "Электротехническая (электротехника)"),
        "031" to DocType("031", "Силовое электрооборудование"),
        "032" to DocType("032", "Автоматика и релейная защита"),
        "033" to DocType("033", "Электрическое освещение (внутреннее) (электроосвещение)"),
        "034" to DocType("034", "Наружное электроосвещение (электроосвещение)"),
        "035" to DocType("035", "Кабельные коммуникации"),
        "036" to DocType("036", "Резерв"),
        "037" to DocType("037", "Резерв"),
        "038" to DocType("038", "Опорно-подвесная система и подопорные конструкции"),
        "039" to DocType("039", "Системы связи (связь и сигнализации)"),
        "040" to DocType("040", "Сооружения транспорта"),
        "041" to DocType("041", "Железнодорожные пути"),
        "042" to DocType("042", "Автомобильные дороги"),
        "043" to DocType("043", "Резерв"),
        "044" to DocType("044", "Резерв"),
        "045" to DocType("045", "Резерв"),
        "046" to DocType("046", "Резерв"),
        "047" to DocType("047", "Резерв"),
        "048" to DocType("048", "Резерв"),
        "049" to DocType("049", "Резерв"),
        "050" to DocType("050", "Инженерные сети и системы"),
        "051" to DocType("051", "Отопление, вентиляция и кондиционирование"),
        "052" to DocType("052", "Водопровод и канализация"),
        "053" to DocType("053", "Спецканализация"),
        "054" to DocType("054", "Спецводоочистка"),
        "055" to DocType("055", "Воздухоснабжение"),
        "056" to DocType("056", "Наружные сети водоснабжения и канализации (наружный водопровод и канализация)"),
        "057" to DocType("057", "Спецхимзащита оборудования и трубопроводов"),
        "058" to DocType("058", "Спецхимзащита строительных конструкций"),
        "059" to DocType("059", "Резерв"),
        "060" to DocType("060", "Транспортно-технологическая (транспортная технология)"),
        "061" to DocType("061", "Обращение с топливом"),
        "062" to DocType("062", "Обращение с отходами (твердыми)"),
        "063" to DocType("063", "Механизация ремонтных работ"),
        "064" to DocType("064", "Топливоподача твердого топлива"),
        "065" to DocType("065", "Резерв"),
        "066" to DocType("066", "Пожарная защита"),
        "067" to DocType("067", "Пожаротушение (технологическая часть)"),
        "068" to DocType("068", "Пожарная сигнализация"),
        "069" to DocType("069", "Автоматизация пожарная"),
        "070" to DocType("070", "АСУ ТП и/или автоматизация комплексная (автоматизация)"),
        "071" to DocType("071", "СКУ тепломеханическая специальность. Теплотехнический контроль"),
        "072" to DocType("072", "СКУ электротехническая специальность. Управление, контроль и автоматика электротехнического оборудования"),
        "073" to DocType("073", "Теплофизика"),
        "074" to DocType("074", "Радиационная защита"),
        "075" to DocType("075", "Радиационная безопасность"),
        "076" to DocType("076", "Система физической защиты (физическая защита)"),
        "077" to DocType("077", "Охрана окружающей среды"),
        "078" to DocType("078", "Радиационный контроль"),
        "079" to DocType("079", "Сметные решения (сметы)"),
        "080" to DocType("080", "Охрана труда и техника безопасности"),
        "081" to DocType("081", "Технико-экономическая часть"),
        "082" to DocType("082", "Резерв"),
        "083" to DocType("083", "Резерв"),
        "084" to DocType("084", "Резерв"),
        "085" to DocType("085", "Учет человеческого фактора"),
        "086" to DocType("086", "ПМТ (полномасштабный тренажер) и технические средства обучения"),
        "087" to DocType("087", "Резерв"),
        "088" to DocType("088", "Управление проектом"),
        "089" to DocType("089", "Качество, экология, профессиональное здоровье и безопасность"),
        "090" to DocType("090", "Очистка сточных вод"),
        "091" to DocType("091", "Системы управления и защиты"),
        "092" to DocType("092", "Система контроля, управления и диагностики"),
        "093" to DocType("093", "Резерв"),
        "094" to DocType("094", "Резерв"),
        "095" to DocType("095", "Резерв"),
        "096" to DocType("096", "Резерв"),
        "097" to DocType("097", "Обследование строительных конструкций"),
        "098" to DocType("098", "Обследование оборудования"),
        "099" to DocType("099", "Резерв"),
        "100" to DocType("100", "Резерв"),
    )

    override fun getDocType(code: String) = map[code]
}