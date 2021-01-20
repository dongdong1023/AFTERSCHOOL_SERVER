package com.wx.base.common;

public interface Constats {

    public static final String CATCH_ERROR_MSG = "[操作出现异常,请联系管理员...]";

    public static final String REQUEST_PARAM_ERROR = "请求参数异常...";

    /*redis存储游戏服务原始数据Key*/
    public static final String REDIS_CSGO_SERVER_DATAS_KEY = "CSGO_ALL_SERVER_DATAS";

    public static final String REDIS_CSGO_SERVER_DATAS_LAST_KEY = "CSGO_ALL_SERVER_DATAS_LAST";

    public static final String REDIS_5E_SERVER_PAUSE_KEY = "5E_SERVER_PAUSE";

    public static final String PAUSE_KEY = "PAUSE_";         //暂停阵容

    public static final String CURRENTUSER = "_currentUser";

    public static final String RESOURCECACHENAME = "resourceCache";

    public static final String RESULT_DEL_DREAM_ERROR = "请删除战队内成员,再执行操作";

    public static final String RESULE_EDIT_MATCH_ERROR = "请选择两组不同的战队";

    public static final String RESULE_MATCH_ISNULL_ERROR = "未查询到该比赛";
    
    public static final String RESULE_MATCH_HAVING_MATCH = "有正在进行的赛事,请先[关播]后再执行此操作...";
    
    public static final String RESULE_MATCH_BEGIN_ERROR = "检测到对阵战队中不存在队员,请补充队员后尝试操作！";

    public static final String RESULE_MATCH_SCORE_ISNULL_ERROR = "未找到到比分数据";

    public static final String RESULE_MEMBER_DREAMCODE_ISNULL_ERROR = "队员所属战队不能为空";

    public static final String RESULE_MEMBER_ISNULL_ERROR = "未查询到该队员";

    public static final String RESULE_DREAM_ISNULL_ERROR = "未查询到该战队";

    public static final String RESULE_MEMBER_CHECKKEY_ERROR = "信息库中已经存在此SteamID信息,请重新输入...";

    public static final String RESULE_USER_ISNULL_ERROR = "添加用户信息不能为空";



    /*
     * 监听解析JSON-Key
     */

    public static final String MAP_KEY = "map"; // 说明在游戏中

    public static final String ALL_PLAYER_KEY = "allplayers"; // 所有玩家信息JSON KEY
    
    public static final String ROUND_KEY = "round";	//回合数

    public static final String MATCH_STATS = "match_stats";    //JSON用户击杀信息

    public static final String PAGE_MEMBERINFO_KEY = "memberInfo";    //跳转个人信息页面Key

    public static final String BESTPLAYER_T_KEY = "bestPlayerT";    //最佳选手T阵容

    public static final String BESTPLAYER_CT_KEY = "bestPlayerCT";    //最佳选手T阵容

    public static final String BESTPLAYER = "BEST";            //最多击杀

    public static final String BESTPLAYER_AWP = "AWP";                //最佳狙击手

    /**
     * CSGO服务器log-Key
     */

    public static final String ROUND_START_KEY = "World triggered \"Round_Start\"";    //标识回合开始

    public static final String ROUND_END_KEY = "World triggered \"Round_End\"";    //标识回合结束

    public static final String ROUND_WINNER_RADIO_KEY = "SFUI_Notice_";            //标识胜利方

    public static final String MATCH_SWITCH_TEAM = "World triggered \"Match_SwitchTeam\"";    //标识回合结束

    public static final String GAME_SERVER_5E_PAUSE_STRAT = "Pause start";    //5E服务器中标识暂停:技术/战术暂停

    public static final String GAME_SERVER_5E_PAUSE_END = "Pause end";    //5E服务器中标识暂停结束

    public static final String ALIVE_PLAYERS_KEY = "Alive players";     //回合结束后场上存活玩家

    public static final String ATTACKED_KEY = "attacked";    //伤害统计KEY

    public static final String KILLED_KEY = "killed";        //特殊武器击杀KEY

    public static final String WEAPON_KEY = "WEAPON";        //JSON数据中武器KEY

    public static final String WEAPON_AWP_KEY = "awp";        //AWP击杀

    public static final String DAMAGE_KEY = "DAMAGE";        //伤害累计KEY

    public static final String HEALTH_KEY = "HEALTH";        //LOG剩余血量KEY

    public static final String GAMENAME_KEY = "GAMENAME";    //LOG中队员信息(游戏名、SteamId32)

    public static final String GAMENAME_DEATH_KEY = "GAMENAME_DEATH";    //LOG中阵亡/承受伤害队员信息(游戏名、SteamId32)

    public static final String PAUSE_TYPE5E_KEY = "pauseType";  //战术暂停/技术暂停

    public static final String PAUSE_TEAM5E_KEY = "pauseTeam";  //暂停方CT/T

    public static final String PAUSE_TYPE_TEC = "TAC";          //战术暂停

    public static final String PAUSE_TYPE_TECH = "TECH";          //技术暂停

    public static final String ROUND_WINNER_TEAM_KEY = "ROUND_WINNER_TEAM"; //本回合获胜方

    public static final String ROUND_ALIVE_PLAYERS_KEY = "ROUND_ALIVE_PLAYERS"; //Redis存储单回合存活信息

    public static final String LIVING_ROUND_KEY = "LIVING_ROUND";	//回合数


    /** Mysql备份还原*/

    public static final String BACKUP_SQL_EXT = ".sql";                       //SQL拓展名

    public static final String BACKUP_DEFAULT_FILE_NAME = "imba_csgo";   //默认备份文件名

    public static final String BACKUP_DEFAULT_FILE_NAME_ALL = BACKUP_DEFAULT_FILE_NAME + BACKUP_SQL_EXT; //默认备份文件名+类型

    public static final String DELETE_FILE_DEFAULT_ERROR = "系统默认备份无法删除";
}
