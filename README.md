Social Club 主要功能：

- 个人领地、小组领地（由Cadmus完成）
- 图形化的领地权限设置（Cadmus只提供了命令）
  - Cadmus需要兼容Create
    [thexaero/opac-fabric-create-support: Adds Create mod support to chunk protection in the Open Parties and Claims mod. (github.com)](https://github.com/thexaero/opac-fabric-create-support/tree/main)
    ↑ 上方为参考代码
- 图形化的小组设置（原版的/team命令）:
  - 需要覆写原版的/team命令，改为socialclub的/team命令
  - 组的增删查改仍然按照原版方法来
  - Cadmus的小组领地功能已经默认集成了原版的方法，所以不需要做改动
  - 图形化需要满足增删查改全部在GUI中完成
- 细节优化（例如小地图的GUI、踏足领地后的提示与禁止破坏提示等）
  - 由于SocialClub的区块领地划分功能需要小地图的平面地图来完成，所以和小地图的代码深度绑定
  - 暂且使用 VoxelMap 的重制版本（[VoxelMap Updated - MC百科|最大的Minecraft中文MOD百科 (mcmod.cn)](https://www.mcmod.cn/class/9382.html)），MIT 协议，可能会直接include其中，也可能会作为必要前置
  - 需要修改VoxelMap的GUI部分来完成区块划分功能
- 好友系统（好友背包同传、贸易等）
  - /friend命令
  - GUI的操作，例如下蹲右键玩家打开玩家界面等，非好友可以添加好友，而好友可以通过这个界面来达到背包同传、交易金币等功能
  - 好友之间可以成立小组
- 一些实用命令（如/hat、/top等等）
  - /hat [ItemStack]：可以将手中物品或指定的ItemStack（可选参数）放置脑袋上
  - /top <Player>：可以将玩家传送至地表（准确来说是最高处可含有的方块），未来可能会搞层数机制
- 语音聊天
- LuckPerms集成
  - Prefix（即玩家头衔）：当玩家没有加入组的情况下，默认为[玩家]。而加入组之后则会变成[组名]。Prefix具有颜色，会根据小组的颜色来修改Prefix字底的颜色
  - LuckPerms管理领地权限
- 未来可能支持跨服聊天、背包同步等等