
public enum MenuOption 
{
	ADD(1),
	DELETE(2),
	SPECIFIC(3),
	LIST(4),
	EXIT(5);

	private final int value;

	private MenuOption(int value)
	{
		this.value = value;
	}
}
