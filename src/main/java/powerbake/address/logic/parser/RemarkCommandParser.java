package powerbake.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static powerbake.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static powerbake.address.logic.parser.CliSyntax.PREFIX_REMARK;

import powerbake.address.commons.core.index.Index;
import powerbake.address.commons.exceptions.IllegalValueException;
import powerbake.address.logic.commands.RemarkCommand;
import powerbake.address.logic.parser.exceptions.ParseException;
import powerbake.address.model.person.Remark;

public class RemarkCommandParser implements Parser<RemarkCommand> {
    
    public RemarkCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_REMARK);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemarkCommand.MESSAGE_USAGE), 
                    ive);
        }

        Remark remark = new Remark(argMultimap.getValue(PREFIX_REMARK).orElse(""));
        return new RemarkCommand(index, remark);
    }
}