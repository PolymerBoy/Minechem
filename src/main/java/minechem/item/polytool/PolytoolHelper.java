package minechem.item.polytool;

import minechem.item.element.ElementAlloyEnum;
import minechem.item.element.ElementEnum;
import minechem.item.polytool.types.*;

import java.util.HashMap;

public class PolytoolHelper
{

	public static boolean loaded;
	public static HashMap<ElementEnum, Class<PolytoolUpgradeType>> types = new HashMap();
	public static Class[] typeClasses =
	{
		PolytoolTypeArgon.class, PolytoolTypeBeryllium.class, PolytoolTypeMagnesium.class, PolytoolTypeBoron.class, PolytoolTypeMercury.class, PolytoolTypeBromine.class, PolytoolTypeNeon.class, PolytoolTypeCaesium.class,
		PolytoolTypeNickel.class, PolytoolTypeCalcium.class, PolytoolTypeNitrogen.class, PolytoolTypeCarbon.class, PolytoolTypeOxygen.class, PolytoolTypeChlorine.class, PolytoolTypePhosphorus.class, PolytoolTypeChromium.class,
		PolytoolTypePlatnium.class, PolytoolTypeFluorine.class, PolytoolTypeRubidium.class, PolytoolTypeFrancium.class, PolytoolTypeSilicon.class, PolytoolTypeGold.class, PolytoolTypeSilver.class, PolytoolTypeHelium.class, PolytoolTypeSodium.class,
		PolytoolTypeHydrogen.class, PolytoolTypeSulfur.class, PolytoolTypeIron.class, PolytoolTypeTitanium.class, PolytoolTypeKrypton.class, PolytoolTypeUranium.class, PolytoolTypeLead.class, PolytoolTypeZirconium.class
	};

	public static PolytoolUpgradeType getTypeFromElement(ElementEnum element, float power)
	{
		if (!loaded)
		{
			loadTypes();
		}
		for (ElementAlloyEnum alloy : ElementAlloyEnum.values())
		{
			if (alloy.element == element)
			{
				return new PolytoolTypeAlloy(alloy, power);
			}

		}
		PolytoolUpgradeType upgrade = null;
		try
		{
			if (types.get(element) == null)
			{
				return null;
			}
			upgrade = types.get(element).newInstance();
		} catch (InstantiationException e)
		{

			e.printStackTrace();
		} catch (IllegalAccessException e)
		{

			e.printStackTrace();
		}
		upgrade.power = power;
		return upgrade;
	}

	private static void loadTypes()
	{
		for (Class clazz : typeClasses)
		{
			try
			{
				clazz.newInstance();
			} catch (InstantiationException e)
			{

				e.printStackTrace();
			} catch (IllegalAccessException e)
			{

				e.printStackTrace();
			}
		}
		loaded = true;
	}

}
