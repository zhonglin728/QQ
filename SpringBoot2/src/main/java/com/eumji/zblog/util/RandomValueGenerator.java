package com.eumji.zblog.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * 随机生成器
 * Random Value Generator
 *
 * @author theboboy
 * @version v1.0
 */
public final class RandomValueGenerator {

    private static final String CN_SEX_MALE = "男";
    private static final String CN_SEX_FEMALE = "女";
    private static final String EN_SEX_MALE = "M";
    private static final String EN_SEX_FEMALE = "F";

    //中文姓
    private static char[] cnFirstNames;
    private static int cfnLength;

    //中文男名
    private static char[] cnMaleLastNames;
    private static int cmlnLength;

    //中文女名
    private static char[] cnFemaleLastNames;
    private static int cflnLength;

    //English Male Firstname
    private static String[] enMaleFirstNames;
    private static int emfnLength;

    //English Female Firstname
    private static String[] enFemaleFirstNames;
    private static int effnLength;

    //English Lastname
    private static String[] enLastNames;
    private static int elnLength;

    private static Random ran;

    private static StringBuffer buffer;

    private static SimpleDateFormat fmt;

    //初始化
    //Initialize
    static {
        String temp = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵堪汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董梁杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗丁宣贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏家封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘景詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍却璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后荆红游竺权逯盖後桓公";
        cnFirstNames = temp.toCharArray();
        cfnLength = cnFirstNames.length;

        temp = "远航旭尧英杰俊楠鸿涛伟祺荣轩浩宇晋鹏伯毅逐天雨泽楷瑞建辉致远俊驰烨磊国豪文博黎昕逐日昊祯龙恩玄华元贞巴莫子烨圣卿尚儒施祁善琦正豪峻熙嘉懿煜城鸿煊";
        cnMaleLastNames = temp.toCharArray();
        cmlnLength = cnMaleLastNames.length;

        temp = "钰彤璟雯天瑜婧琪彤萱玥婷媛馨梦涵碧萱慧妍婧琪璟雯雪怡彦歆芮涵笑薇鑫蕾昭雪玉珍美琳欢馨优璇淑颖娅楠惠茜漫妮香茹月婵嫦曦梦洁凌薇依娜若雨雅芙秀影海琼欣然天瑜可岚滢心姝瑗凌菲妍洋佳琦思睿欣溶露雪怡悦";
        cnFemaleLastNames = temp.toCharArray();
        cflnLength = cnFemaleLastNames.length;

        enLastNames = new String[]{"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin",
                "Thompson", "Garcia", "Martinez", "Robinson", "Clark", "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King", "Wright", "Lopez", "Hill", "Scott",
                "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts", "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart",
                "Sanchez", "Morris", "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox", "Howard", "Ward", "Torres", "Peterson", "Gray",
                "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders", "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long", "Patterson",
                "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales", "Bryant", "Alexander", "Russell", "Griffin", "Diaz", "Hayes"};
        elnLength = enLastNames.length;

        enMaleFirstNames = new String[]{"Aaron", "Abbott", "Abel", "Abner", "Abraham", "Adair", "Adam", "Addison", "Adolph", "Adonis", "Adrian", "Ahern", "Alan", "Albert", "Aldrich", "Alexander",
                "Alfred", "Alger", "Algernon", "Allen", "Alston", "Alva", "Alvin", "Alvis", "Amos", "Andre", "Andrew", "Andy", "Angelo", "Augus", "Ansel", "Antony", "Antoine", "Antonio", "Archer",
                "Archibald", "Aries", "Arlen", "Armand", "Armstrong", "Arno", "Arnold", "Arthur", "Arvin", "Asa", "Ashbur", "Atwood", "Aubrey", "August", "Augustine", "Baird", "Baldwin", "Bancroft",
                "Bard", "Barlow", "Baron", "Barret", "Barry", "Bartholomew", "Bart", "Barton", "Basil", "Beau", "Beck", "Ben", "Benedict", "Benjamin", "Benson", "Berg", "Berger", "Bernie", "Bert",
                "Berton", "Bevis", "Bill", "Bing", "Bishop", "Blair", "Blake", "Blithe", "Bob", "Booth", "Borg", "Boris", "Bowen", "Boyce", "Boyd", "Brady", "Brian", "Broderick", "Caesar", "Calvin",
                "Carey", "Carl", "Carr", "Carter", "Cash", "Cecil", "Cedric", "Chad", "Channing", "Chasel", "Christ", "Christian", "Christopher", "Clare", "Clarence", "Clark", "Claude", "Cleveland",
                "Cliff", "Clifford", "Clyde", "Colby", "Colin", "Conrad", "Corey", "Cornelius", "Craig", "Cyril", "Dana", "Daniel", "Darcy", "Darren", "Dats", "Dave", "David", "Dean", "Dennis",
                "Devin", "Dick", "Don", "Donald", "Douglas", "Drew", "Duke", "Duncan", "Dunn", "Dwight", "Dylan", "Earl", "Ed", "Eden", "Edgar", "Edmund", "Edison", "Edward", "Edwiin", "Egbert",
                "Eli", "Elijah", "Elliot", "Ellis", "Elmer", "Elroy", "Elton", "Elvis", "Emmanuel", "Enoch", "Eric", "Fabian", "Felix", "Ferdinand", "Fitch", "Fitzgerald", "Ford", "Francis", "Frank",
                "Franklin", "Frederic", "Gabriel", "Gale", "Gary", "Gavin", "Gene", "Geoffrey", "Geoff", "George", "Gerald", "Gilbert", "Giles", "Glenn", "Goddard", "Godfery", "Gordon", "Greg",
                "Gregary", "Griffith", "Grover", "Gustave", "Hale", "Haley", "Hamiltion", "Hardy", "Harlan", "Harley", "Harold", "Harriet", "Harry", "Harvey", "Hayden", "Heather", "Henry", "Herbert",
                "Herman", "Hilary", "Hiram", "Hobart", "Hugo", "Hyman", "Jack", "Jacob", "James", "Jared", "Jason", "Jay", "Jeff", "Jeffrey", "Jeremy", "Jerome", "Jerry", "Jesse", "Jim", "John",
                "Jonas", "Jonathan", "Joyce", "Julian", "Julius", "Justin", "Kelly", "Ken", "Kent", "Kerr", "Kerwin", "Kevin", "Kim", "King", "Kirk", "Kyle", "Lambert", "Lance", "Larry", "Lawrence",
                "Leif", "Len", "Lennon", "Leo", "Leonard", "Leopold", "Les", "Lester", "Levi", "Lewis", "Lionel", "Louis", "Lucien", "Lyle", "Lyndon", "Lynn", "Magee", "Malcolm", "Mandel", "Marcus",
                "Marico", "Mark", "Marlon", "Marsh", "Marshall", "Martin", "Marvin", "Matt", "Matthew", "Maurice", "Max", "Maximilian", "Maxwell", "Meredith", "Merle", "Merlin", "Michael", "Michell",
                "Mick", "Mike", "Miles", "Milo", "Morgan", "Morton", "Murray", "Myron", "Nat", "Nathan", "Nathaniel", "Neil", "Nelson", "Nicholas", "Nick", "Noah", "Norman", "Norton", "Ogden",
                "Oliver", "Omar", "Orville", "Osborn", "Oscar", "Oswald", "Otis", "Otto", "Owen", "Page", "Parker", "Paul", "Perry", "Pete", "Peter", "Philip", "Porter", "Prescott", "Primo",
                "Rachel", "Ralap", "Randolph", "Raymond", "Regan", "Reginald", "Reuben", "Rex", "Richard", "Robert", "Robin", "Rock", "Rod", "Rodney", "Ronald", "Rory", "Roy", "Rudolf", "Rupert",
                "Ryan", "Sam", "Sampson", "Samuel", "Sandy", "Saxon", "Scott", "Sean", "Sebastian", "Sid", "Sidney", "Silvester", "Simon", "Solomon", "Spencer", "Stan", "Stanford", "Stanley",
                "Steven", "Stev", "Steward", "Tab", "Taylor", "Ted", "Ternence", "Theobald", "Theodore", "Thomas", "Tiffany", "Tim", "Timothy", "Tobias", "Toby", "Todd", "Tom", "Tony", "Tracy",
                "Troy", "Truman", "Tyler", "Tyrone", "Wade", "Walker", "Walter", "Ward", "Warner", "Wayne", "Webb", "Webster", "Wendell", "Werner", "Wilbur", "Will", "William", "Willie", "Winfred",
                "Winston", "Woodrow", "Wordsworth", "Wright", "Wythe", "Valentine", "Verne", "Victor", "Vivian", "Xavier", "Yale", "York", "Zachary", "Zebulon", "Ziv"};
        emfnLength = enMaleFirstNames.length;

        enFemaleFirstNames = new String[]{"Abigail", "Ada", "Adela", "Adelaide", "Afra", "Agatha", "Agnes", "Alberta", "Alexia", "Alice", "Alma", "Althea", "Alva", "Amanda", "Amelia", "Amy",
                "Anastasia", "Andrea", "Angela", "Ann", "Anna", "Annabelle", "Antonia", "April", "Arlene", "Astrid", "Atalanta", "Athena", "Audrey", "Aurora", "Barbara", "Beatrice", "Belinda",
                "Bella", "Bernice", "Bertha", "Beryl", "Bess", "Betsy", "Betty", "Beulah", "Beverly", "Blanche", "Bblythe", "Bonnie", "Breenda", "Bridget ", "Brook", "Bruf", "Bura", "Camille",
                "Candance", "Candice", "Cara", "Carol", "Caroline", "Catherine ", "Cathy", "Cecilia", "Celeste", "Charlotte", "Cherry", "Cheryl", "Chloe", "Christine", "Claire", "Cora ", "Cornelia",
                "Crystal", "Cynthia", "Daisy", "Dale", "Dana", "Daphne", "Darlene", "Dawn", "Debby ", "Deborah", "Deirdre", "Delia", "Denise", "Diana", "Dinah", "Dolores", "Dominic", "Donna",
                "Dora ", "Doreen", "Doris", "Dorothy", "Eartha", "Eden", "Edith", "Edith", "Eileen", "Elaine", "Eleanore ", "Elizabeth", "Ella", "Ellen", "Elma", "Elsa", "Elsie", "Elva", "Elvira",
                "Emma", "Erin", "Eudora", "Eve", "Evelyn", "Gabrielle", "Gail", "Gemma", "Genevieve", "Geraldine", "Gill", "Giselle", "Grace", "Griselda", "Gwendolyn", "Hannah", "Hazel", "Heather",
                "Hedda", "Helen", "Heloise", "Hermosa", "Hilda", "Hilary", "Honey", "Mabel", "Madeline", "Madge", "Maggie", "Mamie", "Mandy", "Marcia", "Margaret", "Marguerite", "Maria", "Marian",
                "Marina", "Marjorie", "Martha", "Martina", "Mary", "Maud", "Maureen", "Mavis", "Maxine", "Mag", "Megan", "Melissa", "Merry", "Michaelia", "Miranda", "Molly", "Monica", "Murray",
                "Myrna", "Nancy", "Naomi", "Natalie", "Natividad", "Nelly", "Nicola", "Nicole", "Nina", "Nora", "Nydia", "Pamela", "Pandora", "Patricia", "Pearl", "Penny", "Phoebe", "Phoenix",
                "Polly", "Poppy", "Prima", "Rachel", "Rae", "Rebecca", "Regina", "Renata", "Rita", "Roberta", "Rose", "Roxanne", "Ruby", "Sabina", "Sally", "Sabrina", "Salome", "Samantha", "Sandra",
                "Sandy", "Sara", "Sarah", "Selena", "Sharon", "Sheila", "Sherry", "Sibyl", "Simona", "Spring", "Stacey", "Susan", "Susanna", "Sylvia", "Tabitha", "Tammy", "Teresa", "Tess", "Thera",
                "Theresa", "Tiffany", "Tina", "Tobey", "Trista", "Valentina", "Valerie", "Vanessa", "Venus", "Vera", "Verna", "Victoria", "Viola", "Virginia", "Vivien", "Wendy", "Winni", "Xanthe",
                "Yvette", "Yvonne", "Zara", "Zenobia", "Zoe", "Zona", "Zora"};
        effnLength = enFemaleFirstNames.length;

        ran = new Random();
        buffer = new StringBuffer();
        fmt = new SimpleDateFormat("yyyyMMdd");
    }

    private RandomValueGenerator() {
    }

    /**
     * 生成中文的性别
     *
     * @return 中文性别中的一个
     * @see #CN_SEX_MALE
     * @see #CN_SEX_FEMALE
     */
    public static String generateRandomChineseSex() {
        return (ran.nextInt() % 2 == 0) ? CN_SEX_MALE : CN_SEX_FEMALE;
    }

    /**
     * 生成随机boolean
     * @return
     */
    public static boolean generateRandomChineseBoolean() {
        return (ran.nextInt() % 2 == 0) ? true : false;
    }

    /**
     * Generate English Sex
     *
     * @return One of English Sex
     * @see #EN_SEX_MALE
     * @see #EN_SEX_FEMALE
     */
    public static String generateRandomEnglishSex() {
        return (ran.nextInt() % 2 == 0) ? EN_SEX_MALE : EN_SEX_FEMALE;
    }

    /**
     * 生成姓名字数
     *
     * @return 2或3
     */
    public static int generateRandomWordsCount() {
        return ran.nextInt(2) + 2;
    }

    /**
     * 生成中文名
     *
     * @param sex   性别
     * @param count 字数
     * @return 中文名
     */
    public static String generateRandomChineseName(String sex, int count) {
        if (!CN_SEX_MALE.equals(sex) && !CN_SEX_FEMALE.equals(sex)) {
            throw new RuntimeException("性别非法");
        }

        char[] lastNames = (CN_SEX_MALE.equals(sex)) ? cnMaleLastNames : cnFemaleLastNames;
        int lnLength = (CN_SEX_MALE.equals(sex)) ? cmlnLength : cflnLength;

        buffer.setLength(0);

        //姓
        buffer.append(cnFirstNames[ran.nextInt(cfnLength)]);

        //名
        for (int i = 0; i < count - 1; i++) {
            buffer.append(lastNames[ran.nextInt(lnLength)]);
        }

        return buffer.toString();
    }

    /**
     * Generate English Name(2 worlds)
     *
     * @param sex Sex
     * @return An English Name
     */
    public static String generateRandomEnglishName(String sex) {
        if (!EN_SEX_MALE.equals(sex) && !EN_SEX_FEMALE.equals(sex)) {
            throw new RuntimeException("Illegal Sex");
        }

        String[] firstNames = (EN_SEX_MALE.equals(sex)) ? enMaleFirstNames : enFemaleFirstNames;
        int fnLength = (EN_SEX_MALE.equals(sex)) ? emfnLength : effnLength;

        buffer.setLength(0);

        //firstname
        buffer.append(firstNames[ran.nextInt(fnLength)]);

        buffer.append(' ');

        //lastname
        buffer.append(enLastNames[ran.nextInt(elnLength)]);

        return buffer.toString();
    }

    /**
     * 生成随机年龄
     * Generate Random Age
     *
     * @param minAge 最小值, Min Age
     * @param maxAge 最大值, Max Age
     * @return minAge <= RETURN VALUE < maxAge
     */
    public static int generateRandomAge(int minAge, int maxAge) {
        if (minAge < 1 || minAge >= maxAge || maxAge > 99) {
            throw new RuntimeException("范围非法, Illegal Range");
        }

        return ran.nextInt(maxAge - minAge) + minAge;
    }

    /**
     * 生成随机生日
     * Generate Random Birthday
     *
     * @param age 年龄(Age)
     * @return 随机生日, A Birthday
     */
    public static Date generateRandomBirthday(int age) {
        if (age < 1 || age > 99) {
            throw new RuntimeException("年龄非法, Illegal Age");
        }

        buffer.setLength(0);

        //年, Year
        int year = Calendar.getInstance().get(Calendar.YEAR);
        buffer.append(year - age);

        //月, Month
        int month = ran.nextInt(12) + 1;
        if (month < 10) {
            buffer.append(0);
        }
        buffer.append(month);

        //日, Day
        int day = 1;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = ran.nextInt(31) + 1;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = ran.nextInt(30) + 1;
                break;
            case 2:
                day = ran.nextInt(27) + 1;
        }
        if (day < 10) {
            buffer.append(0);
        }
        buffer.append(day);

        try {
            return fmt.parse(buffer.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 生成身份证号
     *
     * @param birthday 生日
     * @return 随机身份证号
     */
    public static String generateIdCardNumber(Date birthday) {
        if (birthday == null) {
            throw new RuntimeException("生日非法!");
        }

        buffer.setLength(0);

        //6位地区  以鞍山为例  需要改成目标城市  后期会考虑根据参数生成
        buffer.append("21030");
        buffer.append(ran.nextInt(4) + 1);

        //生日
        buffer.append(fmt.format(birthday));

        //四位随机数  不含X
        buffer.append(ran.nextInt(9000) + 1000);

        //四位随机数  含X
        //buffer.append(ran.nextInt() % 2 == 0 ? ran.nextInt(9000) + 1000 + "" : ran.nextInt(900) + 100 + "X");

        return buffer.toString();
    }

    /**
     * 生成身份证号
     *
     * @param age 年龄
     * @return 随机身份证号
     */
    public static String generateIdCardNumber(int age) {
        if (age < 1 || age > 99) {
            throw new RuntimeException("年龄非法!");
        }

        buffer.setLength(0);

        //6位地区  以鞍山为例  需要改成目标城市  后期会考虑根据参数生成
        buffer.append("21030");
        buffer.append(ran.nextInt(4) + 1);

        //年
        int year = Calendar.getInstance().get(Calendar.YEAR);
        buffer.append(year - age);

        //月
        int month = ran.nextInt(12) + 1;
        if (month < 10) {
            buffer.append(0);
        }
        buffer.append(month);

        //日
        int day = 1;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                day = ran.nextInt(31) + 1;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                day = ran.nextInt(30) + 1;
                break;
            case 2:
                day = ran.nextInt(27) + 1;
        }
        if (day < 10) {
            buffer.append(0);
        }
        buffer.append(day);

        //四位随机数  不含X
        buffer.append(ran.nextInt(9000) + 1000);

        //四位随机数  含X
        //buffer.append(ran.nextInt() % 2 == 0 ? ran.nextInt(9000) + 1000 + "" : ran.nextInt(900) + 100 + "X");

        return buffer.toString();
    }

    /**
     * 生成随机手机号码
     *
     * @return 随机手机号
     */
    public static String generateRandomTelephoneNumber() {
        buffer.setLength(0);

        //第一位
        buffer.append(1);

        /*
         * theboboy原创算法
         * 第二位(3或5或8)
         * 生成基数  0  1  2
         *    *25得 0 25 50
         *    /10得 0  2  5
         *     +3得 3  5  8
         */
        int temp = ran.nextInt(3) * 25 / 10 + 3;
        buffer.append(temp);

        //第三位130-139,150-159,186,187,188
        buffer.append((temp == 8) ? ran.nextInt(3) + 6 : ran.nextInt(10));

        //后八位  未考虑地区等  后期会考虑根据参数生成
        buffer.append(ran.nextInt(90000000) + 10000000);

        return buffer.toString();
    }

}
