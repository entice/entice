/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol


/**
 * State of a moving entity
 */
object MoveState extends Enumeration {
    val NotMoving               = Value("NotMoving")
    val Moving                  = Value("Moving")
}


/**
 * Some animations (emotes)
 */
object Animations extends Enumeration {
    val None                    = Value("none")

    val Beg                     = Value("beg")  
    val Doh                     = Value("doh")  
    val Scare                   = Value("scare")    
    val PointNod                = Value("pointNod")        
    val Sit                     = Value("sit")  
    val Bow                     = Value("bow")  
    val Wave                    = Value("wave")   
    val HighFive                = Value("highFive")        
    val Tired                   = Value("tired")    
    val Rock                    = Value("rock")   
    val IdleChatting            = Value("idleChatting")            
    val HandsOnHeadLookDown     = Value("handsOnHeadLookDown")                      
    val Yawn                    = Value("yawn")   
    val Shoo                    = Value("shoo")   
    val Clap                    = Value("clap")   
    val PickMe1                 = Value("pickMe1")       
    val Jump                    = Value("jump")   
    val Cheer1                  = Value("cheer1")     
    val Pout                    = Value("pout")   
    val Flex                    = Value("flex")   
    val QuickFlex               = Value("quickFlex")         
    val IdlePonder              = Value("idlePonder")          
    val Dance                   = Value("dance")    
    val Agree                   = Value("agree")    
    val Flute                   = Value("flute")    
    val Laugh                   = Value("laugh")    
    val Cough                   = Value("cough")    
    val Kneel                   = Value("kneel")    
    val Cheer2                  = Value("cheer2")     
    val Paper                   = Value("paper")    
    val Clapping                = Value("clapping")       
    val ClappingExcited         = Value("clappingExcited")               
    val Drums                   = Value("drums")    
    val Point                   = Value("point")    
    val Taunt                   = Value("taunt")    
    val CatchBreath1            = Value("catchBreath1")            
    val Shoo2                   = Value("shoo2")    
    val BowHead                 = Value("bowHead")       
    val BothHandsUp             = Value("bothHandsUp")            
    val PickMe2                 = Value("pickMe2")       
    val Attention1              = Value("attention1")         
    val Scratch                 = Value("scratch")      
    val ShakeFist               = Value("shakeFist")         
    val CatchBreath2            = Value("catchBreath2")            
    val Violin                  = Value("violin")     
    val Rank                    = Value("rank")   
    val Guitar                  = Value("guitar")     
    val Ponder                  = Value("ponder")     
    val Attention2              = Value("attention2")         
    val Scissors                = Value("scissors")       
    val StandUp                 = Value("standUp")       
    val Attention3              = Value("attention3")         
}


/**
 * All accessible maps...
 */
object Maps extends Enumeration {
    // internal structure
    class MapVal(name: String, val groupSize: Int, val spawns: List[Coord2D]) extends Val(nextId, name)
    protected final def Value(name: String, groupSize: Int, spawns: List[Coord2D]): MapVal = new MapVal(name, groupSize, spawns)

    // workaround for withName
    final def withMapName(name: String): MapVal = super.withName(name).asInstanceOf[MapVal]

    val HeroesAscent            = Value("HeroesAscent", 8, List(Coord2D(2017, -3241)))
    val TeamArenas              = Value("TeamArenas",   8, List(Coord2D(-1873, 352)))
    val RandomArenas            = Value("RandomArenas", 8, List(Coord2D(3854, 3874)))
}