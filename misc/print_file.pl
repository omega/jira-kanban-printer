#!/usr/bin/env perl

##
## This is a *simple* example with minimal error checking of how to 
## print a file.
##

use strict;
use warnings;

use Net::CUPS;
use Net::CUPS::Destination;

{
    die( "Please give me the name of a file to print!\n" ) if ( @ARGV < 1 );

    my $filename = $ARGV[0];

    my $cups = Net::CUPS->new();

    my $dest = $cups->getDestination();

    die( "You don't have a default printer setup!\n" ) if( !defined( $dest ) );

    print "I am attempting to print $filename to " . $dest->getName() . ".\n";

    # Set some options
    #
    $dest->addOption('landscape', 'on');
    $dest->addOption('sides', 'two-sided-short-edge');
    $dest->addOption('fit-to-page', 'on');

    $dest->printFile( $filename, "Net::CUPS Test Print!" );

    exit( 0 );
}
