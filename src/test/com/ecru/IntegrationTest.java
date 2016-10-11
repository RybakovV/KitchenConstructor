package test.com.ecru;

import com.ecru.DataBaseManager;
import com.ecru.PrintClipboard;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import static com.sun.xml.internal.ws.dump.LoggingDumpTube.Position.Before;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Rybakov Vitaliy on 12.09.2016.
 */
public class IntegrationTest {

    private ConfigurableInputStream in;
    private ByteArrayOutputStream out;
    private DataBaseManager manager;

    @Before
    public void setup(){
        in = new ConfigurableInputStream();
        out = new ByteArrayOutputStream();

        System.setIn(in);
        System.setOut(new PrintStream(out));

        manager = new DataBaseManager();
        manager.connectToDataBase("kitchenkonstructor", "root", "root");



        }

/*
    @Test
    public void testInsertFail() {
        in.add("0");
        in.add("0");
        in.add("0");

        String actusal = getData();
        String expected = "";
        assertEquals(actusal, expected);

    }
*/
    @Test
    public void test21001() {
        in.add("2");
        in.add("100");
        in.add("1");
        in.add("K04-BL_TWIN-106H-570/596_PL-FRN01");
        in.add("K04-BL_TWIN-106H-713/596_LP-FRN01");
        in.add("K04-BL_TWIN-106H-355/596_VOAD_MA-FRN01");
        in.add("K04-BL_TWIN-106H-355/896_VOAD_MA-FRN01");
        in.add("K04-BL_TWIN-106H-570/596_PL-FRN01");
        in.add("K04-BL_TWIN-106H-713/396_LP-FRN01");
        in.add("K04-BL_TWIN-106H-713/446_LP-FRN01");
        in.add("K04-BL_TWIN-106H-713/446_PL-FRN01");
        in.add("K04-BL_TWIN-106H-713/596_M-FRN01");

        PrintClipboard.main(new String[0]);
        String actusal = getData();
        String expected = "Select main front \n" +
                "0: 36th Norde Avenue\n" +
                "1: 38th Elysee Avenue з ручкою\n" +
                "2: 88th Twin street\n" +
                "3: 38th Elysee Avenue\n" +
                "4: 32th Malbec Avenue\n" +
                "5: 98th Elysee Street з ручкою\n" +
                "6: 98th Elysee Street з ручкою LO\n" +
                "7: Royal Insygnata Вишня\n" +
                "8: TRAFFIC 71st Street\n" +
                "9: Рамка A3 Алюміній\n" +
                "10: Рамка A Алюміній\n" +
                "11: Sentima [ 9] Дев'ята симфонія\n" +
                "12: 80th Frame street\n" +
                "13: [ 2] Два щастя\n" +
                "14: BRW Line STARA BASN\n" +
                "15: Пілястри 75 Royal тип G\n" +
                "16: 87th Smooth street\n" +
                "17: Capital 35th Avenue\n" +
                "18: Draggo\n" +
                "19: Royal Diadema\n" +
                "20: Royal Imperor\n" +
                "21: GLADIO\n" +
                "22: Kantana\n" +
                "23: 86th Secret street\n" +
                "24: 99th Mile Street\n" +
                "25: 87th Smooth street з ручкою\n" +
                "26: 33rd Saperavi Avenue\n" +
                "27: 91st Court Street\n" +
                "28: 38th ELYSEE Avenue з ручкою L0\n" +
                "29: BRW Line KARTA\n" +
                "30: Modern Line DIONA з ручкою\n" +
                "31: FRIGG\n" +
                "32: Modern Line TELESTO\n" +
                "33: 77th Simple Street\n" +
                "34: 89th Galleo street\n" +
                "35: 85th Play street аплікація з ручкою\n" +
                "36: 85th Play street з ручкою\n" +
                "37: 85th Play street аплікація\n" +
                "38: 85th Play street Нитка глянець\n" +
                "39: Modern Line DIONA\n" +
                "40: Modern Line HEDAR\n" +
                "41: BRW Line FALA\n" +
                "42: 39th Candia Avenue\n" +
                "43: Рамка Z1 Алюміній\n" +
                "44: Рамка Z9 Алюміній\n" +
                "45: Modern Line SAVANA\n" +
                "46: Kasetta\n" +
                "47: Modern Line SAVANA з ручкою\n" +
                "48: Ambries Вільха Amber\n" +
                "49: Рамка A2\n" +
                "50: Modern Line BOARD\n" +
                "51: [19] Дев'ятнадцяте століття\n" +
                "52: [13] Щаслива тринадцятка\n" +
                "53: Saga\n" +
                "54: [ 8] Восьма спокуса\n" +
                "55: Rubia\n" +
                "56: Nessa\n" +
                "57: Camello\n" +
                "58: 37th Savana Avenue з ручкою L0\n" +
                "59: Melos\n" +
                "60: 37th Savana Avenue з ручкою L146\n" +
                "61: 33rd Saperavi Avenue ШПОН\n" +
                "62: 84th Board street з ручкою\n" +
                "63: PINUX\n" +
                "64: Пілястри 75 Sentima тип B\n" +
                "65: Пілястри 75 Traffic тип RH\n" +
                "66: Пілястри 75 Traffic тип RV\n" +
                "67: Пілястри 75 Capital тип RV\n" +
                "68: 43rd Luvak Avenue\n" +
                "69: 98th Elysee Street\n" +
                "70: 36th Norde Avenue з ручкою L0\n" +
                "71: Пілястри 75 Sentima тип A\n" +
                "72: 45th Bevely Avenue\n" +
                "73: 36th Norde Avenue з ручкою L146\n" +
                "74: Пілястри 75 Royal тип B\n" +
                "75: Пілястри 75 Royal тип H\n" +
                "76: Pasjonata\n" +
                "77: Пілястри 75 Royal тип A\n" +
                "78: Пілястри 75 Royal тип D\n" +
                "79: Пілястри 75 Sentima тип C\n" +
                "80: Пілястри 75 Royal тип E\n" +
                "81: Пілястри 75 Sentima тип F\n" +
                "82: Пілястри 75 Sentima тип G\n" +
                "83: Рамка Венге Z11\n" +
                "84: Рамка Срібло мат Z11\n" +
                "85: Рамка Біла Z11\n" +
                "86: Рамка Біла Z11 Білий (9003)\n" +
                "87: Корпус\n" +
                "Select color front: \n" +
                "0: 101 (Білий)\n" +
                "1: 102 (Білий Гладкий)\n" +
                "2: 104 (Береза Сибірська)\n" +
                "3: 105 (Клен Кремовий)\n" +
                "4: 106 (Клен)\n" +
                "5: 110 (Сосна Селедин)\n" +
                "6: 111 (Дуб Кориця)\n" +
                "7: 115 (Вишня)\n" +
                "8: 116 (Махаон)\n" +
                "9: 121 (Дуб Золотий)\n" +
                "10: 124 (Кальвадос)\n" +
                "11: 126 (Вишня Коньяк)\n" +
                "12: 127 (Темна Ваніль)\n" +
                "13: 133 (Вільха Медова)\n" +
                "14: 135 (Вишня Корейська)\n" +
                "15: 137 (Груша)\n" +
                "16: 138 (Яблуня Темна)\n" +
                "17: 139 (Вишня Малага)\n" +
                "18: 140 (Венге)\n" +
                "19: 141 (Дуб Чесаний)\n" +
                "20: 143 (Дуб Античний)\n" +
                "21: 144 (Дуб Вертикальний)\n" +
                "22: 145 (Акація)\n" +
                "23: 146 (Самоа Тік)\n" +
                "24: 147 (Вишня Портофіна)\n" +
                "25: 148 (Бежевий)\n" +
                "26: 149 (Мідно Червоний)\n" +
                "27: 150 (Апельсиновий)\n" +
                "28: 151 (Черешня Антична)\n" +
                "29: 152 (Білий Лак)\n" +
                "30: 153 (Бордо Полоск)\n" +
                "31: 154 (Ваніль Лак)\n" +
                "32: 155 (Вишня Італійська)\n" +
                "33: 156 (Сосна Антична)\n" +
                "34: 157 (Зебрано Темная)\n" +
                "35: 158 (Зебрано Светлая)\n" +
                "36: 159 (Тик)\n" +
                "37: 160 (Модрина)\n" +
                "38: 161 (Модрина Світла)\n" +
                "39: 162 (Слива)\n" +
                "40: 163 (Дуб Линара)\n" +
                "41: 164 (Чорний Глянець)\n" +
                "42: 165 (Бежевий Глянець)\n" +
                "43: 166 (Біла Фантазія)\n" +
                "44: 167 (Сосна Коричнева)\n" +
                "45: 168 (Ясень Messina)\n" +
                "46: 169 (Горіх Елегант)\n" +
                "47: 170 (Бук Roxan)\n" +
                "48: 171 (Червоний Глянець)\n" +
                "49: 172 (Ясень Античний)\n" +
                "50: 173 (Оливка)\n" +
                "51: 176 (Дуб венге магія)\n" +
                "52: 177 (Хебан темний)\n" +
                "53: 178 (Хебан світлий)\n" +
                "54: 179 (Жасмін темний)\n" +
                "55: 180 (Жасмін світлий)\n" +
                "56: 181 (Ясень світлий)\n" +
                "57: 182 (Ясень табако)\n" +
                "58: 183 (Горіх аматі)\n" +
                "59: 185 (Вишня сакура)\n" +
                "60: 186 (Горіх памплона)\n" +
                "61: 187 (Слива валіс)\n" +
                "62: 188 (Мелінга чорна)\n" +
                "63: 189 (Каштан античний)\n" +
                "64: 190 (Бук мільтенберг)\n" +
                "65: 191 (Білий CANADIAN)\n" +
                "66: 192 (Сірий)\n" +
                "67: 193 (Сірий глянець)\n" +
                "68: 194 (Хебан світлий глянець)\n" +
                "69: 195 (Горіх срібний глянець)\n" +
                "70: 196 (Дуб венге магія глянець)\n" +
                "71: 197 Горіх Autore\n" +
                "72: 198 Mali Wenge\n" +
                "73: 199 Melanzana (супер матовий)\n" +
                "74:  200 Mocca (супер матовий)\n" +
                "75: 201 (Шираз венге)\n" +
                "76: 202 (Дуб Енканто)\n" +
                "77: 203 (Дуб Кантербері світлий)\n" +
                "78: 204 (Дуб сонома)\n" +
                "79: 205 (Пісочно сірий глянець)\n" +
                "80: 206 (Фісташка глянець)\n" +
                "81: 207 (Баклажан глянець)\n" +
                "82: 210 (Сірий малі)\n" +
                "83: 211 (Червоний глянець)\n" +
                "84: 212 (Чорно-коричневий глянець)\n" +
                "85: 213 (Дуб Санремо)\n" +
                "86: 214 (Дуб Сонома темний)\n" +
                "87: 215 (Зебрано Сірий )\n" +
                "88: 216 (Зебрано Коричневий )\n" +
                "89: 217 (Дакар мат)\n" +
                "90: 218 (Лава мат)\n" +
                "91: 219 (Сіро-коричневий мат)\n" +
                "92: 220 (Чорний мат)\n" +
                "93: 221 (Антрацит)\n" +
                "94: 222 (Трюфель мат)\n" +
                "95: 223 (Мюсель мат)\n" +
                "96: 224 (Алебастр мат)\n" +
                "97: 101H (Білий, горизонтально)\n" +
                "98: 104H (Береза Сибірська, горизонтально)\n" +
                "99: 105H (Клен Кремовий, горизонтально)\n" +
                "100: 106H (Клен, горизонтально)\n" +
                "101: 110H (Сосна Селедин, горизонтально)\n" +
                "102: 111H (Дуб Кориця, горизонтально)\n" +
                "103: 116H (Махаон, горизонтально)\n" +
                "104: 121H (Дуб Золотий, горизонтально)\n" +
                "105: 124H (Кальвадос, горизонтально)\n" +
                "106: 126H (Вишня Коньяк, горизонтально)\n" +
                "107: 133H (Вільха Медова, горизонтально)\n" +
                "108: 135H (Вишня Корейська, горизонтально)\n" +
                "109: 137H (Груша, горизонтально)\n" +
                "110: 138H (Яблуня Темна, горизонтально)\n" +
                "111: 139H (Вишня Малага, горизонтально)\n" +
                "112: 140H (Венге, горизонтально)\n" +
                "113: 141H (Дуб Чесаний, горизонтально)\n" +
                "114: 143H (Дуб Античний, горизонтально)\n" +
                "115: 144H (Дуб Вертикальний, горизонтально)\n" +
                "116: 145H (Акація, горизонтально)\n" +
                "117: 146H (Самоа Тік, горизонтально)\n" +
                "118: 147H (Вишня Портофіна, горизонтально)\n" +
                "119: 151H (Черешня Антична, горизонтально)\n" +
                "120: 155H (Вишня Італійська, горизонтально)\n" +
                "121: 156H (Сосна Антична, горизонтально)\n" +
                "122: 157H (Зебрано Темная, горизонтально)\n" +
                "123: 158H (Зебрано Светлая, горизонтально)\n" +
                "124: 159H (Тик, горизонтально)\n" +
                "125: 160H (Модрина, горизонтально)\n" +
                "126: 161H (Модрина Світла, горизонтально)\n" +
                "127: 162H (Слива, горизонтально)\n" +
                "128: 163H (Дуб Линара, горизонтально)\n" +
                "129: 167H (Сосна Коричнева, горизонтально)\n" +
                "130: 168H (Ясень Messina, горизонтально)\n" +
                "131: 169H (Горіх Елегант, горизонтально)\n" +
                "132: 170H (Бук Roxan, горизонтально)\n" +
                "133: 172H (Ясень Античний, горизонтально)\n" +
                "134: 176Н (Дуб венге магія, горизонтально)\n" +
                "135: 177H (Хебан темний, горизонтально)\n" +
                "136: 178H (Хебан світлий, горизонтально)\n" +
                "137: 179H (Жасмін темний, горизонтально)\n" +
                "138: 180H (Жасмін світлий, горизонтально)\n" +
                "139: 181H (Ясень світлий, горизонтально)\n" +
                "140: 182H (Ясень табако, горизонтально)\n" +
                "141: 183H (Горіх аматі, горизонтально)\n" +
                "142: 185H (Вишня сакура, горизонтально)\n" +
                "143: 186H (Горіх памплона горизонтально)\n" +
                "144: 187H (Слива валіс горизонтально)\n" +
                "145: 188H (Мелінга чорна горизонтально)\n" +
                "146: 189H (Каштан античний горизонтально)\n" +
                "147: 190H (Бук мільтенберг горизонтально)\n" +
                "148: 194H (Хебан світлий глянець горизонтально)\n" +
                "149: 195H (Горіх срібний глянець горизонтально)\n" +
                "150: 196H (Дуб венге магія глянець)\n" +
                "151: 197H Горіх Autore горизонтально\n" +
                "152: 198H Mali Wenge горизонтально\n" +
                "153: 201H (Шираз венге горизонтально)\n" +
                "154: 202H (Дуб Енканто горизонтально)\n" +
                "155: 203H (Дуб Кантербері світлий горизонтально)\n" +
                "156: 204H (Дуб сонома горизонтально)\n" +
                "157: 208H (Лазер глянець горизонтально)\n" +
                "158: 210H (Сірий малі горизонтально)\n" +
                "159: 213H (Дуб Санремо горизонтально)\n" +
                "160: 214Н (Дуб Сонома темний горизонтально)\n" +
                "161: 215H (Зебрано Сірий горизонтально)\n" +
                "162: 216H (Зебрано Коричневий горизонтально)\n" +
                "163: 221Н (Антрацит горизонтально)\n" +
                "Select color korpus: \n" +
                "0: BE\n" +
                "1: Беж Ясний\n" +
                "2: BI\n" +
                "3: BU\n" +
                "4: Чорний\n" +
                "5: Дуб Гірський\n" +
                "6: Дуб Амарі\n" +
                "7: Дуб Canadian\n" +
                "8: Дуб Каштановий\n" +
                "9: Дуб Latte\n" +
                "10: Дуб Macchiato\n" +
                "11: Дуб Milano\n" +
                "12: Дуб пісочний\n" +
                "13: DW\n" +
                "14: GRR\n" +
                "15: Heban Ammara\n" +
                "16: Heban ammara глянець\n" +
                "17: Heban Makasar\n" +
                "18: Heban makasar глянець\n" +
                "19: Heban yoruba\n" +
                "20: Heban Yoruba глянець\n" +
                "21: Ясень\n" +
                "22: Ясень Натуральний\n" +
                "23: Ясень натуральний глянець\n" +
                "24: Каштан\n" +
                "25: Каштан Середній\n" +
                "26: KLN\n" +
                "27: Махонь\n" +
                "28: Махонь глянець\n" +
                "29: Вільха Сан\n" +
                "30: Palisander Alpi\n" +
                "31: Palisander alpi глянець\n" +
                "32: Palisander глянець\n" +
                "33: SZ\n" +
                "34: Сірий Гренола\n" +
                "35: Венге Амарі\n" +
                "36: Венге Амарі глянець\n" +
                "37: Венге\n" +
                "38: Венге Чорний\n" +
                "39: Венге чорний глянець\n" +
                "40: Вишня Електра\n" +
                "41: Вишня Примавера\n" +
                "42: WISE\n" +
                "43: Вишня Портофіно\n" +
                "FRN_393/596_O 1\n" +
                "K04-BL_TWIN-106H-393/596_O-FRN01\tФасад BRW Line TWIN 106H (Клен, горизонтально) 393/596_O\t471.5899963378906\t1\t471.5899963378906\n" +
                "FRN_570/596 1\n" +
                "K04-BL_TWIN-106H-570/596-FRN01 do not finde in price. Correct please:\n" +
                "K04-BL_TWIN-106H-570/596-FRN01K04-BL_TWIN-106H-570/596_PL-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 570/596_PL\t963.6400146484375\t1\t963.6400146484375\n" +
                "FRN_713/596 1\n" +
                "K04-BL_TWIN-106H-713/596-FRN01 do not finde in price. Correct please:\n" +
                "K04-BL_TWIN-106H-713/596-FRN01K04-BL_TWIN-106H-713/596_LP-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 713/596_LP\t1204.9200439453125\t1\t1204.9200439453125\n" +
                "FRN-1247/146_C 1\n" +
                "K04-BL_TWIN-106H-1247/146_C-FRN02\tФасад 88th Twin street 106H (Клен, горизонтально) 1247/146_C\t531.719970703125\t1\t531.719970703125\n" +
                "FRN-140/896_ST 1\n" +
                "K04-BL_TWIN-106H-140/896_ST-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 140/896_ST\t355.6099853515625\t1\t355.6099853515625\n" +
                "FRN-284/896_ST 2\n" +
                "K04-BL_TWIN-106H-284/896_ST-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 284/896_ST\t720.72998046875\t2\t1441.4599609375\n" +
                "FRN-355/596_OAG 1\n" +
                "K04-BL_TWIN-106H-355/596_OAG-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 355/596_OAG\t600.1900024414062\t1\t600.1900024414062\n" +
                "FRN-355/596_VOAD_MAL 1\n" +
                "K04-BL_TWIN-106H-355/596_VOAD_MAL-FRN01 do not finde in price. Correct please:\n" +
                "K04-BL_TWIN-106H-355/596_VOAD_MAL-FRN01K04-BL_TWIN-106H-355/596_VOAD_MA-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 355/596_VOAD_MA\t825.6400146484375\t1\t825.6400146484375\n" +
                "FRN-355/896_OAG 1\n" +
                "K04-BL_TWIN-106H-355/896_OAG-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 355/896_OAG\t902.469970703125\t1\t902.469970703125\n" +
                "FRN-355/896_VOAD_MAL 1\n" +
                "K04-BL_TWIN-106H-355/896_VOAD_MAL-FRN01 do not finde in price. Correct please:\n" +
                "K04-BL_TWIN-106H-355/896_VOAD_MAL-FRN01K04-BL_TWIN-106H-355/896_VOAD_MA-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 355/896_VOAD_MA\t1258.239990234375\t1\t1258.239990234375\n" +
                "FRN-393/596_O 1\n" +
                "K04-BL_TWIN-106H-393/596_O-FRN01\tФасад BRW Line TWIN 106H (Клен, горизонтально) 393/596_O\t471.5899963378906\t1\t471.5899963378906\n" +
                "FRN-500/596_O 1\n" +
                "K04-BL_TWIN-106H-500/596_O-FRN01\tФасад BRW Line TWIN 106H (Клен, горизонтально) 500/596_O\t601.2100219726562\t1\t601.2100219726562\n" +
                "FRN-570/596 1\n" +
                "K04-BL_TWIN-106H-570/596-FRN01 do not finde in price. Correct please:\n" +
                "K04-BL_TWIN-106H-570/596-FRN01K04-BL_TWIN-106H-570/596_PL-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 570/596_PL\t963.6400146484375\t1\t963.6400146484375\n" +
                "FRN-713/146_C 1\n" +
                "K04-BL_TWIN-106H-713/146_C-FRN02\tФасад 88th Twin street 106H (Клен, горизонтально) 713/146_C\t302.510009765625\t1\t302.510009765625\n" +
                "FRN-713/296_C 2\n" +
                "K04-BL_TWIN-106H-713/296_C-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 713/296_C\t600.1900024414062\t2\t1200.3800048828125\n" +
                "FRN-713/396 1\n" +
                "K04-BL_TWIN-106H-713/396-FRN01 do not finde in price. Correct please:\n" +
                "K04-BL_TWIN-106H-713/396-FRN01K04-BL_TWIN-106H-713/396_LP-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 713/396_LP\t799.0900268554688\t1\t799.0900268554688\n" +
                "FRN-713/40_BNW 1\n" +
                "K04-BL_TWIN-106H-713/40_BNW-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 713/40_BNW\t206.74000549316406\t1\t206.74000549316406\n" +
                "FRN-713/446 1\n" +
                "K04-BL_TWIN-106H-713/446-FRN01 do not finde in price. Correct please:\n" +
                "K04-BL_TWIN-106H-713/446-FRN01K04-BL_TWIN-106H-713/446_LP-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 713/446_LP\t902.469970703125\t1\t902.469970703125\n" +
                "FRN-713/446 3\n" +
                "K04-BL_TWIN-106H-713/446-FRN01 do not finde in price. Correct please:\n" +
                "K04-BL_TWIN-106H-713/446-FRN01K04-BL_TWIN-106H-713/446_PL-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 713/446_PL\t902.469970703125\t3\t2707.409912109375\n" +
                "FRN-713/596 1\n" +
                "K04-BL_TWIN-106H-713/596-FRN01 do not finde in price. Correct please:\n" +
                "K04-BL_TWIN-106H-713/596-FRN01K04-BL_TWIN-106H-713/596_M-FRN01\tФасад 88th Twin street 106H (Клен, горизонтально) 713/596_M\t1204.9200439453125\t1\t1204.9200439453125\n" +
                "KOR-D_90/82 1\n" +
                "KOR-D_90/82 1\n" +
                "K04-KORPUS-BEJ-D_90/82-KOR+AKC01\tКорпус Беж Ясний D_90/82\t2047.239990234375\t1\t2047.239990234375\n" +
                "KOR-DC_15/207 1\n" +
                "KOR-DC_15/207 1\n" +
                "K04-KORPUS-BEJ-DC_15/207-KOR+AKC01\tКорпус Беж Ясний DC_15/207\t2877.89990234375\t1\t2877.89990234375\n" +
                "KOR-DC_30/82 2\n" +
                "KOR-DC_30/82 2\n" +
                "K04-KORPUS-BEJ-DC_30/82-KOR+AKC01\tКорпус Беж Ясний DC_30/82\t1084.6700439453125\t2\t2169.340087890625\n" +
                "KOR-DKNW_120/82 1\n" +
                "KOR-DKNW_120/82 1\n" +
                "K04-KORPUS-BEJ-DKNW_120/82-KOR+AKC01\tКорпус Беж Ясний DKNW_120/82\t2187.449951171875\t1\t2187.449951171875\n" +
                "KOR-DPP37_45O_60/207 1\n" +
                "KOR-DPP37_45O_60/207 1\n" +
                "K04-KORPUS-BEJ-DPP37_45O_60/207-KOR+AKC02\tКорпус Беж Ясний DPP37_45O_60/207\t3757.56005859375\t1\t3757.56005859375\n" +
                "KOR-DST/5_90/82 1\n" +
                "KOR-DST/5_90/82 1\n" +
                "K04-KORPUS-BEJ-DST/5_90/82-KOR+AKC01\tКорпус Беж Ясний DST/5_90/82\t1536.5699462890625\t1\t1536.5699462890625\n" +
                "KOR-G_45/72 2\n" +
                "KOR-G_45/72 2\n" +
                "K04-KORPUS-BEJ-G_45/72-KOR+AKC01\tКорпус Беж Ясний G_45/72\t1173.3299560546875\t2\t2346.659912109375\n" +
                "KOR-GNWU_60/72 1\n" +
                "KOR-GNWU_60/72 1\n" +
                "K04-KORPUS-BEJ-GNWU_60/72-KOR+AKC01\tКорпус Беж Ясний GNWU_60/72\t2368.43994140625\t1\t2368.43994140625\n" +
                "KOR-GOA_60/72 1\n" +
                "KOR-GOA_60/72 1\n" +
                "K04-KORPUS-BEJ-GOA_60/72-KOR+AKC01\tКорпус Беж Ясний GOA_60/72\t2597.81005859375\t1\t2597.81005859375\n" +
                "KOR-GOA_90/72 1\n" +
                "KOR-GOA_90/72 1\n" +
                "K04-KORPUS-BEJ-GOA_90/72-KOR+AKC01\tКорпус Беж Ясний GOA_90/72\t2845.31005859375\t1\t2845.31005859375\n" +
                "KOR-GOO_60/50 1\n" +
                "KOR-GOO_60/50 1\n" +
                "K04-KORPUS-BEJ-GOO_60/50-KOR+AKC02\tКорпус Беж Ясний GOO_60/50\t878.4400024414062\t1\t878.4400024414062\n" +
                "K04-KORPUS-BK-AVENTOS_HF_A2-AKC02 1\n" +
                "K04-KORPUS-BK-AVENTOS_HF_A2-AKC02 1\n" +
                "K04-KORPUS-BK-AVENTOS_HF_A2-AKC02\tПідйомний механізм AVENTOS_HF_A2\t2115.0\t1\t2115.0\n" +
                "K04-KORPUS-BK-AVENTOS_HF_C1-AKC02 2\n" +
                "K04-KORPUS-BK-AVENTOS_HF_C1-AKC02 2\n" +
                "K04-KORPUS-BK-AVENTOS_HF_C1-AKC02\tПідйомний механізм AVENTOS_HF_C1\t553.27001953125\t2\t1106.5400390625\n" +
                "K04-KORPUS-BK-AVENTOS_HF_D1-AKC01 2\n" +
                "K04-KORPUS-BK-AVENTOS_HF_D1-AKC01 2\n" +
                "K04-KORPUS-BK-AVENTOS_HF_D1-AKC01\tПідйомний механізм AVENTOS_HF_D1\t287.7200012207031\t2\t575.4400024414062\n" +
                "K04-KORPUS-BK-AVENTOS_HF_B1-AKC02 2\n" +
                "K04-KORPUS-BK-AVENTOS_HF_B1-AKC02 2\n" +
                "K04-KORPUS-BK-AVENTOS_HF_B1-AKC02\tПідйомний механізм AVENTOS_HF_B1\t1471.0799560546875\t2\t2942.159912109375\n" +
                "K04-KORPUS-BK-AVENTOS_HF_A1-AKC02 1\n" +
                "K04-KORPUS-BK-AVENTOS_HF_A1-AKC02 1\n" +
                "K04-KORPUS-BK-AVENTOS_HF_A1-AKC02\tПідйомний механізм AVENTOS_HF_A1\t1884.8399658203125\t1\t1884.8399658203125\n" +
                "K04-KORPUS-BK-ROZW_MAXI_A1-AKC01 3\n" +
                "K04-KORPUS-BK-ROZW_MAXI_A1-AKC01 3\n" +
                "K04-KORPUS-BK-ROZW_MAXI_A1-AKC01\tПідйомний механізм ROZW_MAXI_A1\t372.510009765625\t3\t1117.530029296875\n" +
                "K04-KORPUS-SZ-DST_90_W-KOR01 2\n" +
                "K04-KORPUS-SZ-DST_90_W-KOR01 2\n" +
                "K04-KORPUS-SZ-DST_90_W-KOR01\tШухляда Tandembox SZ DST_90_W\t2800.580078125\t2\t5601.16015625\n" +
                "K04-KORPUS-SZ-DSI_90_N-KOR01 1\n" +
                "K04-KORPUS-SZ-DSI_90_N-KOR01 1\n" +
                "K04-KORPUS-SZ-DSI_90_N-KOR01\tШухляда Intivo SZ DSI_90_N\t2054.639892578125\t1\t2054.639892578125\n";
        assertEquals(actusal, expected);

    }


    @Test
    public void testInsertZeroZeroZOne() {
        in.add("0");
        in.add("0");
        in.add("1");
        PrintClipboard.main(new String[0]);
        String actusal = getData();
        String expected = "Select main front \n" +
                "0: 36th Norde Avenue\n" +
                "1: 38th Elysee Avenue з ручкою\n" +
                "2: 88th Twin street\n" +
                "3: 38th Elysee Avenue\n" +
                "4: 32th Malbec Avenue\n" +
                "5: 98th Elysee Street з ручкою\n" +
                "6: 98th Elysee Street з ручкою LO\n" +
                "7: Royal Insygnata Вишня\n" +
                "8: TRAFFIC 71st Street\n" +
                "9: Рамка A3 Алюміній\n" +
                "10: Рамка A Алюміній\n" +
                "11: Sentima [ 9] Дев'ята симфонія\n" +
                "12: 80th Frame street\n" +
                "13: [ 2] Два щастя\n" +
                "14: BRW Line STARA BASN\n" +
                "15: Пілястри 75 Royal тип G\n" +
                "16: 87th Smooth street\n" +
                "17: Capital 35th Avenue\n" +
                "18: Draggo\n" +
                "19: Royal Diadema\n" +
                "20: Royal Imperor\n" +
                "21: GLADIO\n" +
                "22: Kantana\n" +
                "23: 86th Secret street\n" +
                "24: 99th Mile Street\n" +
                "25: 87th Smooth street з ручкою\n" +
                "26: 33rd Saperavi Avenue\n" +
                "27: 91st Court Street\n" +
                "28: 38th ELYSEE Avenue з ручкою L0\n" +
                "29: BRW Line KARTA\n" +
                "30: Modern Line DIONA з ручкою\n" +
                "31: FRIGG\n" +
                "32: Modern Line TELESTO\n" +
                "33: 77th Simple Street\n" +
                "34: 89th Galleo street\n" +
                "35: 85th Play street аплікація з ручкою\n" +
                "36: 85th Play street з ручкою\n" +
                "37: 85th Play street аплікація\n" +
                "38: 85th Play street Нитка глянець\n" +
                "39: Modern Line DIONA\n" +
                "40: Modern Line HEDAR\n" +
                "41: BRW Line FALA\n" +
                "42: 39th Candia Avenue\n" +
                "43: Рамка Z1 Алюміній\n" +
                "44: Рамка Z9 Алюміній\n" +
                "45: Modern Line SAVANA\n" +
                "46: Kasetta\n" +
                "47: Modern Line SAVANA з ручкою\n" +
                "48: Ambries Вільха Amber\n" +
                "49: Рамка A2\n" +
                "50: Modern Line BOARD\n" +
                "51: [19] Дев'ятнадцяте століття\n" +
                "52: [13] Щаслива тринадцятка\n" +
                "53: Saga\n" +
                "54: [ 8] Восьма спокуса\n" +
                "55: Rubia\n" +
                "56: Nessa\n" +
                "57: Camello\n" +
                "58: 37th Savana Avenue з ручкою L0\n" +
                "59: Melos\n" +
                "60: 37th Savana Avenue з ручкою L146\n" +
                "61: 33rd Saperavi Avenue ШПОН\n" +
                "62: 84th Board street з ручкою\n" +
                "63: PINUX\n" +
                "64: Пілястри 75 Sentima тип B\n" +
                "65: Пілястри 75 Traffic тип RH\n" +
                "66: Пілястри 75 Traffic тип RV\n" +
                "67: Пілястри 75 Capital тип RV\n" +
                "68: 43rd Luvak Avenue\n" +
                "69: 98th Elysee Street\n" +
                "70: 36th Norde Avenue з ручкою L0\n" +
                "71: Пілястри 75 Sentima тип A\n" +
                "72: 45th Bevely Avenue\n" +
                "73: 36th Norde Avenue з ручкою L146\n" +
                "74: Пілястри 75 Royal тип B\n" +
                "75: Пілястри 75 Royal тип H\n" +
                "76: Pasjonata\n" +
                "77: Пілястри 75 Royal тип A\n" +
                "78: Пілястри 75 Royal тип D\n" +
                "79: Пілястри 75 Sentima тип C\n" +
                "80: Пілястри 75 Royal тип E\n" +
                "81: Пілястри 75 Sentima тип F\n" +
                "82: Пілястри 75 Sentima тип G\n" +
                "83: Рамка Венге Z11\n" +
                "84: Рамка Срібло мат Z11\n" +
                "85: Рамка Біла Z11\n" +
                "86: Рамка Біла Z11 Білий (9003)\n" +
                "87: Корпус\n" +
                "Select color front: \n" +
                "0: Дуб Canadian\n" +
                "1: Дуб Каштановий\n" +
                "2: Дуб Latte\n" +
                "3: Дуб Macchiato\n" +
                "4: Дуб медовий\n" +
                "5: Дуб Platinum\n" +
                "6: Дуб пісочний\n" +
                "7: Сосна Біла\n" +
                "8: Сосна Табако\n" +
                "Select color korpus: \n" +
                "0: BE\n" +
                "1: Беж Ясний\n" +
                "2: BI\n" +
                "3: BU\n" +
                "4: Чорний\n" +
                "5: Дуб Гірський\n" +
                "6: Дуб Амарі\n" +
                "7: Дуб Canadian\n" +
                "8: Дуб Каштановий\n" +
                "9: Дуб Latte\n" +
                "10: Дуб Macchiato\n" +
                "11: Дуб Milano\n" +
                "12: Дуб пісочний\n" +
                "13: DW\n" +
                "14: GRR\n" +
                "15: Heban Ammara\n" +
                "16: Heban ammara глянець\n" +
                "17: Heban Makasar\n" +
                "18: Heban makasar глянець\n" +
                "19: Heban yoruba\n" +
                "20: Heban Yoruba глянець\n" +
                "21: Ясень\n" +
                "22: Ясень Натуральний\n" +
                "23: Ясень натуральний глянець\n" +
                "24: Каштан\n" +
                "25: Каштан Середній\n" +
                "26: KLN\n" +
                "27: Махонь\n" +
                "28: Махонь глянець\n" +
                "29: Вільха Сан\n" +
                "30: Palisander Alpi\n" +
                "31: Palisander alpi глянець\n" +
                "32: Palisander глянець\n" +
                "33: SZ\n" +
                "34: Сірий Гренола\n" +
                "35: Венге Амарі\n" +
                "36: Венге Амарі глянець\n" +
                "37: Венге\n" +
                "38: Венге Чорний\n" +
                "39: Венге чорний глянець\n" +
                "40: Вишня Електра\n" +
                "41: Вишня Примавера\n" +
                "42: WISE\n" +
                "43: Вишня Портофіно\n" +
                "FRN_393/596_O 1\n" +
                "K04-ML_NORDE-DCN-393/596_O-FRN02\tФасад 36th Norde Avenue Дуб Canadian 393/596_O\t1282.8900146484375\t1\t1282.8900146484375\n" +
                "FRN_570/596 1\n" +
                "K04-ML_NORDE-DCN-570/596-FRN01\tФасад 36th Norde Avenue Дуб Canadian 570/596\t1727.3499755859375\t1\t1727.3499755859375\n" +
                "FRN_713/596 1\n" +
                "K04-ML_NORDE-DCN-713/596-FRN01\tФасад 36th Norde Avenue Дуб Canadian 713/596\t2088.280029296875\t1\t2088.280029296875\n" +
                "FRN-1247/146_C 1\n" +
                "K04-ML_NORDE-DCN-1247/146_C-FRN02\tФасад 36th Norde Avenue Дуб Canadian 1247/146_C\t1201.3199462890625\t1\t1201.3199462890625\n" +
                "FRN-140/896_ST 1\n" +
                "K04-ML_NORDE-DCN-140/896_ST-FRN01\tФасад 36th Norde Avenue Дуб Canadian 140/896_ST\t1013.5\t1\t1013.5\n" +
                "FRN-284/896_ST 2\n" +
                "K04-ML_NORDE-DCN-284/896_ST-FRN01\tФасад 36th Norde Avenue Дуб Canadian 284/896_ST\t1399.1700439453125\t2\t2798.340087890625\n" +
                "FRN-355/596_OAG 1\n" +
                "K04-ML_NORDE-DCN-355/596_OAG-FRN01\tФасад 36th Norde Avenue Дуб Canadian 355/596_OAG\t1187.6199951171875\t1\t1187.6199951171875\n" +
                "FRN-355/596_VOAD_MAL 1\n" +
                "K04-ML_NORDE-DCN-355/596_VOAD_MAL-FRN01\tФасад 36th Norde Avenue Дуб Canadian 355/596_VOAD_MAL\t1873.949951171875\t1\t1873.949951171875\n" +
                "FRN-355/896_OAG 1\n" +
                "K04-ML_NORDE-DCN-355/896_OAG-FRN01\tФасад 36th Norde Avenue Дуб Canadian 355/896_OAG\t1659.02001953125\t1\t1659.02001953125\n" +
                "FRN-355/896_VOAD_MAL 1\n" +
                "K04-ML_NORDE-DCN-355/896_VOAD_MAL-FRN01\tФасад 36th Norde Avenue Дуб Canadian 355/896_VOAD_MAL\t2412.489990234375\t1\t2412.489990234375\n" +
                "FRN-393/596_O 1\n" +
                "K04-ML_NORDE-DCN-393/596_O-FRN02\tФасад 36th Norde Avenue Дуб Canadian 393/596_O\t1282.8900146484375\t1\t1282.8900146484375\n" +
                "FRN-500/596_O 1\n" +
                "K04-ML_NORDE-DCN-500/596_O-FRN02\tФасад 36th Norde Avenue Дуб Canadian 500/596_O\t1551.6800537109375\t1\t1551.6800537109375\n" +
                "FRN-570/596 1\n" +
                "K04-ML_NORDE-DCN-570/596-FRN01\tФасад 36th Norde Avenue Дуб Canadian 570/596\t1727.3499755859375\t1\t1727.3499755859375\n" +
                "FRN-713/146_C 1\n" +
                "K04-ML_NORDE-DCN-713/146_C-FRN02\tФасад 36th Norde Avenue Дуб Canadian 713/146_C\t762.6099853515625\t1\t762.6099853515625\n" +
                "FRN-713/296_C 2\n" +
                "K04-ML_NORDE-DCN-713/296_C-FRN02\tФасад 36th Norde Avenue Дуб Canadian 713/296_C\t1200.75\t2\t2401.5\n" +
                "FRN-713/396 1\n" +
                "K04-ML_NORDE-DCN-713/396-FRN01\tФасад 36th Norde Avenue Дуб Canadian 713/396\t1497.030029296875\t1\t1497.030029296875\n" +
                "FRN-713/40_BNW 1\n" +
                "K04-ML_NORDE-DCN-713/40_BNW-FRN01\tФасад 36th Norde Avenue Дуб Canadian 713/40_BNW\t849.7999877929688\t1\t849.7999877929688\n" +
                "FRN-713/446 1\n" +
                "K04-ML_NORDE-DCN-713/446-FRN01\tФасад 36th Norde Avenue Дуб Canadian 713/446\t1644.25\t1\t1644.25\n" +
                "FRN-713/446 3\n" +
                "K04-ML_NORDE-DCN-713/446-FRN01\tФасад 36th Norde Avenue Дуб Canadian 713/446\t1644.25\t3\t4932.75\n" +
                "FRN-713/596 1\n" +
                "K04-ML_NORDE-DCN-713/596-FRN01\tФасад 36th Norde Avenue Дуб Canadian 713/596\t2088.280029296875\t1\t2088.280029296875\n" +
                "KOR-D_90/82 1\n" +
                "KOR-D_90/82 1\n" +
                "K04-KORPUS-BEJ-D_90/82-KOR+AKC01\tКорпус Беж Ясний D_90/82\t2047.239990234375\t1\t2047.239990234375\n" +
                "KOR-DC_15/207 1\n" +
                "KOR-DC_15/207 1\n" +
                "K04-KORPUS-BEJ-DC_15/207-KOR+AKC01\tКорпус Беж Ясний DC_15/207\t2877.89990234375\t1\t2877.89990234375\n" +
                "KOR-DC_30/82 2\n" +
                "KOR-DC_30/82 2\n" +
                "K04-KORPUS-BEJ-DC_30/82-KOR+AKC01\tКорпус Беж Ясний DC_30/82\t1084.6700439453125\t2\t2169.340087890625\n" +
                "KOR-DKNW_120/82 1\n" +
                "KOR-DKNW_120/82 1\n" +
                "K04-KORPUS-BEJ-DKNW_120/82-KOR+AKC01\tКорпус Беж Ясний DKNW_120/82\t2187.449951171875\t1\t2187.449951171875\n" +
                "KOR-DPP37_45O_60/207 1\n" +
                "KOR-DPP37_45O_60/207 1\n" +
                "K04-KORPUS-BEJ-DPP37_45O_60/207-KOR+AKC02\tКорпус Беж Ясний DPP37_45O_60/207\t3757.56005859375\t1\t3757.56005859375\n" +
                "KOR-DST/5_90/82 1\n" +
                "KOR-DST/5_90/82 1\n" +
                "K04-KORPUS-BEJ-DST/5_90/82-KOR+AKC01\tКорпус Беж Ясний DST/5_90/82\t1536.5699462890625\t1\t1536.5699462890625\n" +
                "KOR-G_45/72 2\n" +
                "KOR-G_45/72 2\n" +
                "K04-KORPUS-BEJ-G_45/72-KOR+AKC01\tКорпус Беж Ясний G_45/72\t1173.3299560546875\t2\t2346.659912109375\n" +
                "KOR-GNWU_60/72 1\n" +
                "KOR-GNWU_60/72 1\n" +
                "K04-KORPUS-BEJ-GNWU_60/72-KOR+AKC01\tКорпус Беж Ясний GNWU_60/72\t2368.43994140625\t1\t2368.43994140625\n" +
                "KOR-GOA_60/72 1\n" +
                "KOR-GOA_60/72 1\n" +
                "K04-KORPUS-BEJ-GOA_60/72-KOR+AKC01\tКорпус Беж Ясний GOA_60/72\t2597.81005859375\t1\t2597.81005859375\n" +
                "KOR-GOA_90/72 1\n" +
                "KOR-GOA_90/72 1\n" +
                "K04-KORPUS-BEJ-GOA_90/72-KOR+AKC01\tКорпус Беж Ясний GOA_90/72\t2845.31005859375\t1\t2845.31005859375\n" +
                "KOR-GOO_60/50 1\n" +
                "KOR-GOO_60/50 1\n" +
                "K04-KORPUS-BEJ-GOO_60/50-KOR+AKC02\tКорпус Беж Ясний GOO_60/50\t878.4400024414062\t1\t878.4400024414062\n" +
                "K04-KORPUS-BK-AVENTOS_HF_A2-AKC02 1\n" +
                "K04-KORPUS-BK-AVENTOS_HF_A2-AKC02 1\n" +
                "K04-KORPUS-BK-AVENTOS_HF_A2-AKC02\tПідйомний механізм AVENTOS_HF_A2\t2115.0\t1\t2115.0\n" +
                "K04-KORPUS-BK-AVENTOS_HF_C1-AKC02 2\n" +
                "K04-KORPUS-BK-AVENTOS_HF_C1-AKC02 2\n" +
                "K04-KORPUS-BK-AVENTOS_HF_C1-AKC02\tПідйомний механізм AVENTOS_HF_C1\t553.27001953125\t2\t1106.5400390625\n" +
                "K04-KORPUS-BK-AVENTOS_HF_D1-AKC01 2\n" +
                "K04-KORPUS-BK-AVENTOS_HF_D1-AKC01 2\n" +
                "K04-KORPUS-BK-AVENTOS_HF_D1-AKC01\tПідйомний механізм AVENTOS_HF_D1\t287.7200012207031\t2\t575.4400024414062\n" +
                "K04-KORPUS-BK-AVENTOS_HF_B1-AKC02 2\n" +
                "K04-KORPUS-BK-AVENTOS_HF_B1-AKC02 2\n" +
                "K04-KORPUS-BK-AVENTOS_HF_B1-AKC02\tПідйомний механізм AVENTOS_HF_B1\t1471.0799560546875\t2\t2942.159912109375\n" +
                "K04-KORPUS-BK-AVENTOS_HF_A1-AKC02 1\n" +
                "K04-KORPUS-BK-AVENTOS_HF_A1-AKC02 1\n" +
                "K04-KORPUS-BK-AVENTOS_HF_A1-AKC02\tПідйомний механізм AVENTOS_HF_A1\t1884.8399658203125\t1\t1884.8399658203125\n" +
                "K04-KORPUS-BK-ROZW_MAXI_A1-AKC01 3\n" +
                "K04-KORPUS-BK-ROZW_MAXI_A1-AKC01 3\n" +
                "K04-KORPUS-BK-ROZW_MAXI_A1-AKC01\tПідйомний механізм ROZW_MAXI_A1\t372.510009765625\t3\t1117.530029296875\n" +
                "K04-KORPUS-SZ-DST_90_W-KOR01 2\n" +
                "K04-KORPUS-SZ-DST_90_W-KOR01 2\n" +
                "K04-KORPUS-SZ-DST_90_W-KOR01\tШухляда Tandembox SZ DST_90_W\t2800.580078125\t2\t5601.16015625\n" +
                "K04-KORPUS-SZ-DSI_90_N-KOR01 1\n" +
                "K04-KORPUS-SZ-DSI_90_N-KOR01 1\n" +
                "K04-KORPUS-SZ-DSI_90_N-KOR01\tШухляда Intivo SZ DSI_90_N\t2054.639892578125\t1\t2054.639892578125\n";
        assertEquals(actusal, expected);

    }

    private String getData() {
        try {
            return new String(out.toByteArray(), "UTF-8").replace("\r\n","\n");
        } catch (UnsupportedEncodingException e) {
            return e.getMessage();
        }
    }
}
