package it.unibs.ing.elaborato.util;

import it.unibs.ing.elaborato.model.conversionElement.ConversionElement;
import it.unibs.ing.elaborato.model.district.District;
import it.unibs.ing.elaborato.model.hierarchy.Category;
import it.unibs.ing.elaborato.model.hierarchy.Couple;
import it.unibs.ing.elaborato.model.hierarchy.LeafCategory;
import it.unibs.ing.elaborato.model.hierarchy.NotLeafCategory;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposal;
import it.unibs.ing.elaborato.model.proposal.ExchangeProposals;
import it.unibs.ing.elaborato.model.proposal.State;

import java.util.List;

/**
 * Classe che sovrintende la view dell'applicativo contenente metodi statici.
 */
public class Printer
{
	public static String align (String s, int larghezza)
	{
		StringBuilder res = new StringBuilder(larghezza);
		if (larghezza <= s.length())
			res.append(s.substring(larghezza));
		else
		{
			int spaziPrima = (larghezza - s.length()) / 2;
			int spaziDopo = larghezza - spaziPrima - s.length();
			for (int i = 1; i <= spaziPrima; i++)
				res.append(" ");

			res.append(s);

			for (int i = 1; i <= spaziDopo; i++)
				res.append(" ");
		}
		return res.toString();
	}

	public static String columnize (String s, int lineWidth)
	{
		StringBuilder formattedString = new StringBuilder();
		int currentLineWidth = 0;

		for (char c : s.toCharArray()) {
			if (currentLineWidth >= lineWidth && Character.isWhitespace(c))
			{
				formattedString.append(System.lineSeparator());
				currentLineWidth = 0;
			}
			else
			{
				formattedString.append(c);
				currentLineWidth++;
			}
		}
		return formattedString.toString();
	}

	public static String printLeaves(List<LeafCategory> leaves)
	{
		StringBuilder result = new StringBuilder();

		int i = 1;
		for (LeafCategory leaf : leaves) {
			result.append(i++).append(Constants.SEPARATOR).append(leaf.getName()).append(Constants.NEW_LINE);
		}
		return result.toString();
	}

	public static String printHierarchy(Category radice, int depth)
	{
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < depth; i++)
			result.append(Constants.TAB);

		result.append(radice.getName()).append(Constants.NEW_LINE);

		try {
			for (Category figlio : radice.getChildren())
				result.append(printHierarchy(figlio, depth + 1));
		} catch (UnsupportedOperationException ignored) {}
		return result.toString();
	}

	public static String printHierarchyRoots(List<NotLeafCategory> hierarchies)
	{
		StringBuilder result = new StringBuilder();

		int i = 1;
		for (Category nodo : hierarchies)
			result.append(i++).append(Constants.SEPARATOR).append(nodo.getName()).append(Constants.NEW_LINE);

		return result.toString();
	}

	public static String printDistrict(District district)
	{
		StringBuilder result = new StringBuilder();
		result.append(Constants.NAME_DISTRICT_MESSAGE).append(district.getName()).append(Constants.NEW_LINE);
		result.append(Constants.TERRITORIES_DISTRICT_MESSAGE);

		for (int i = 0; i < district.getMunicipalities().size(); i++)
			if (i != district.getMunicipalities().size() - 1)
				result.append(district.getMunicipalities().get(i)).append(Constants.COMMA);
			else
				result.append(district.getMunicipalities().get(i));
		result.append(Constants.NEW_LINE);

		return result.toString();
	}

	public static String printDistricts(List<District> districts)
	{
		StringBuilder result = new StringBuilder();

		for(District district : districts)
			result.append(printDistrict(district)).append(Constants.NEW_LINE);

		return result.toString();
	}

	public static String printNumberedDistricts(List<District> districts)
	{
		StringBuilder result = new StringBuilder();
		int i = 0;

		for(District district : districts)
			result.append(++i).append(Constants.SEPARATOR).append(Printer.printDistrict(district)).append(Constants.NEW_LINE);

		return result.toString();
	}

	public static String printRemainingConversionFactor(List<ConversionElement> conversionElements)
	{
		StringBuilder result = new StringBuilder();
		result.append(Constants.REMAINING_CONVERSION_FACTOR_MESSAGE);
		result.append(Constants.NEW_LINE);

		int i = 1;
		for(ConversionElement conversionElement : conversionElements)
			result.append(Utility.addBlank(Integer.toString(i++), 4)).append(Constants.SEPARATOR).append(Printer.printCouple(conversionElement.getCouple())).append(Constants.NEW_LINE);

		return result.toString();
	}

	public static String printConversionElements(List<ConversionElement> conversionElements)
	{
		StringBuilder result = new StringBuilder();

		result.append(Constants.NEW_LINE);
		result.append(Printer.align(Constants.INSERT_CONV_FACT, Constants.MENU_LINE_SIZE));
		result.append(Constants.DOUBLE_NEW_LINE);
		result.append(Constants.FIXED_CONVERSION_FACTOR_MESSAGE);
		result.append(Constants.NEW_LINE);

		for (ConversionElement elem : conversionElements)
			result.append(printConversionElement(elem));

		return result.toString();
	}

	public static String printConversionElement(ConversionElement conversionElement)
	{
		String formatFactor = String.format(Constants.NUMBER_FORMAT, Math.round(conversionElement.getConversionFactor() * 100) / 100.);
		return (printCouple(conversionElement.getCouple()) + Constants.COLONS + Constants.LIGHT_BLUE_FORMAT + formatFactor + Constants.RESET_FORMAT + Constants.NEW_LINE);
	}

	public static String printCouple(Couple couple)
	{
		return Utility.addBlank(couple.getFirstLeaf().getName(), 50) + Constants.COUPLE_SEPARATOR + couple.getSecondLeaf().getName();
	}

	public static String printExtendHierarchy(Category root)
	{
		StringBuilder result = new StringBuilder();

		if(!root.hasChildren())
			result.append(printLeaf((LeafCategory) root));
		else
		{
			result.append(printNotLeaf((NotLeafCategory)root));
			for (Category child : root.getChildren())
				try {
					result.append(printExtendHierarchy(child));
				} catch (UnsupportedOperationException ignored) {}
		}
		return result.toString();
	}

	public static String printLeaf(LeafCategory leaf)
	{
		StringBuilder result = new StringBuilder();

		result.append(Constants.NAME_MESSAGE + Constants.SEPARATOR).append(leaf.getName()).append(Constants.NEW_LINE);
		result.append(Constants.DOMAIN_MESSAGE + Constants.SEPARATOR).append(leaf.getDomain()).append(Constants.NEW_LINE);
		if(!(leaf.getDescription() == null))
			result.append(Constants.DESCRIPTION_MESSAGE + Constants.SEPARATOR).append(leaf.getDescription()).append(Constants.NEW_LINE);
		result.append(Constants.NEW_LINE);

		return result.toString();
	}

	public static String printNotLeaf(NotLeafCategory root)
	{
		StringBuilder result = new StringBuilder();

		result.append(Constants.NAME_MESSAGE + Constants.SEPARATOR).append(root.getName()).append(Constants.NEW_LINE);
		if(root.getDomain() != null)
			result.append(Constants.DOMAIN_MESSAGE + Constants.SEPARATOR).append(root.getDomain()).append(Constants.NEW_LINE);
		if(root.getDescription() != null)
			result.append(Constants.DESCRIPTION_MESSAGE + Constants.SEPARATOR).append(root.getDescription()).append(Constants.NEW_LINE);
		result.append(Constants.FIELD_MESSAGE + Constants.SEPARATOR).append(root.getField()).append(Constants.NEW_LINE);
		result.append(Constants.NEW_LINE);

		return result.toString();
	}

	public static String printExchangeProposal(ExchangeProposal proposal)
	{
		StringBuilder result = new StringBuilder();
		result.append(Constants.REQUEST_MESSAGE + Constants.SQUARE_BRACKETS1).append(proposal.getCouple().getFirstLeaf().getName()).append(Constants.COMMA).append(proposal.getHoursRequest()).append(Constants.SQUARE_BRACKETS2);
		result.append(Constants.NEW_LINE);
		result.append(Constants.OFFER_MESSAGE + Constants.SQUARE_BRACKETS1).append(proposal.getCouple().getSecondLeaf().getName()).append(Constants.COMMA).append(Constants.LIGHT_BLUE_FORMAT).append(proposal.getHoursOffered()).append(Constants.RESET_FORMAT).append(Constants.SQUARE_BRACKETS2);
		result.append(Constants.NEW_LINE);

		return result.toString();
	}

	public static String printExchangeProposalWithSatus(ExchangeProposal proposal)
	{
		StringBuilder result = new StringBuilder();
		result.append(Constants.NEW_LINE);
		if(proposal.getState() == State.OPEN)
			result.append(Constants.GREEN_FORMAT + Constants.OPEN + Constants.RESET_FORMAT);
		else if(proposal.getState() == State.CLOSED)
			result.append(Constants.RED_FORMAT + Constants.CLOSED + Constants.RESET_FORMAT);
		else
			result.append(Constants.YELLOW_FORMAT + Constants.WITHDRAWN + Constants.RESET_FORMAT);
		result.append(Constants.NEW_LINE).append(printExchangeProposal(proposal));
		return result.toString();
	}

	public static String printExchangeProposals(List<ExchangeProposal> proposals)
	{
		StringBuilder result = new StringBuilder();

		for(ExchangeProposal elem : proposals)
		{
			result.append(printExchangeProposal(elem));
			result.append(Constants.NEW_LINE);
		}

		return result.toString();
	}

	public static String printExchangeProposalsWithConsumer(List<ExchangeProposal> proposals)
	{
		StringBuilder result = new StringBuilder();

		for(ExchangeProposal elem : proposals)
		{
			result.append(Constants.CONSUMER);
			result.append(elem.getOwner().getUsername());
			result.append(Constants.NEW_LINE);
			result.append(Constants.MAIL);
			result.append(elem.getOwner().getEmail());
			result.append(Constants.NEW_LINE);
			result.append(printExchangeProposal(elem));
			result.append(Constants.NEW_LINE);
		}

		return result.toString();
	}

	public static String printNumberedExchangeProposals(List<ExchangeProposal> proposals)
	{
		StringBuilder result = new StringBuilder();
		result.append(Constants.DOUBLE_NEW_LINE);
		int i = 1;
		for(ExchangeProposal elem : proposals)
		{
			result.append(Constants.UNDERLINE + Constants.PROPOSAL).append(i++).append(Constants.NEW_LINE).append(Constants.RESET_FORMAT);
			result.append(printExchangeProposal(elem));
			result.append(Constants.NEW_LINE);
		}

		return result.toString();
	}

	public static String printClosedSets(List<ExchangeProposals> closedSets)
	{
		StringBuilder result = new StringBuilder();

		int i = 0;
		for(ExchangeProposals elem : closedSets)
		{
			result.append(String.format(Constants.CLOSED_SETS, ++i));
			result.append(Constants.NEW_LINE);
			result.append(Printer.printExchangeProposalsWithConsumer(elem.getExchangeProposals()));
			result.append(Constants.NEW_LINE);
		}

		return result.toString();
	}
}
