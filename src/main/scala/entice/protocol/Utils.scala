/**
 * For copyright information see the LICENSE document.
 */

package entice.protocol

import play.api.libs.json._
import info.akshaal.json.jsonmacro._


/**
 * Resembles a 2d vector or point (depending on the context)
 */
case class Coord2D(x: Float, y: Float) extends Typeable


/**
 * Wraps a java.util.UUID.
 */
case class UUID(mostSigBytes: Long, leastSigBytes: Long) extends Typeable

object UUID {
    def apply(id: java.util.UUID): UUID = new UUID(id.getMostSignificantBits, id.getLeastSignificantBits)
    def apply(): UUID = apply(java.util.UUID.randomUUID)

    val Invalid = UUID(0, 0)
}


/**
 * State of a moving entity
 */
object MoveState extends Enumeration {
    val NotMoving    = Value("NotMoving")
    val Moving       = Value("Moving")
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


object Utils {

    // serialization
    implicit def uuidFields     = allFields[UUID]   ('jsonate)
    implicit def coord2dFields  = allFields[Coord2D]('jsonate)

    // deserialization
    implicit def uuidFactory    = factory[UUID]     ('fromJson)
    implicit def coord2dFactory = factory[Coord2D]  ('fromJson)
}