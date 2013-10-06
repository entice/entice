namespace Protocol
{
        public abstract class Typeable : Serializable
        {
                public string type;

                protected Typeable()
                {
                        type = GetType().Name;
                }
        }
}